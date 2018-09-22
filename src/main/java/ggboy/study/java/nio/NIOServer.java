package ggboy.study.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import mustry.common.utils.array.ArrayUtil;
import mustry.common.utils.bytee.ByteUtil;
import mustry.common.utils.string.StringUtil;

public class NIOServer {
	
	// 通道管理器
	private Selector selector;
	private boolean isRun = false;

	// 获得一个ServerSocket通道，并对该通道做一些初始化的工作
	public void initServer(int port) throws IOException {
		// 获得一个通道管理器
		this.selector = Selector.open();
		// 获得一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// 设置通道为非阻塞
		serverChannel.configureBlocking(false);
		// 将该通道对应的ServerSocket绑定到port端口
		serverChannel.socket().bind(new InetSocketAddress(port));
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		// 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		isRun = true;
	}

	// 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	public void listen() {
		while (this.isRun) {
			try {
				// 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
				this.selector.select();
				// 获得selector中选中的项的迭代器，选中的项为注册的事件
				Set<SelectionKey> keys = this.selector.selectedKeys();
				if (keys == null || keys.size() == 0) {
					continue;
				}
				Iterator<SelectionKey> iter = keys.iterator();
				while (iter != null && iter.hasNext()) {
					SelectionKey key = iter.next();
					iter.remove(); // 删除已选的key,以防重复处理
					if (key.isAcceptable()) {
						this.acceptable(key);
					} else if (key.isReadable()) {
						this.read(key);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	// 客户端请求连接事件
	private void acceptable(SelectionKey key) throws IOException {
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		// 获得和客户端连接的通道
		SocketChannel channel = server.accept();
		// 设置成非阻塞
		channel.configureBlocking(false);
		// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
		channel.register(this.selector, SelectionKey.OP_READ);
	}

	// 处理读取客户端发来的信息 的事件
	private void read(SelectionKey key) throws IOException {
		// 服务器可读取消息:得到事件发生的Socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		BufferReader reader = (BufferReader) key.attachment();
		if (reader == null) {
			reader = new NioReader();
			key.attach(reader);
		}
		
		int count = reader.doRead(channel);
		if (count == -1) {
			// -1 说明通道被客户端关闭或者已经挂了
			this.close();
		}
		
		// 如果未读取完毕则直接返回等待下次selector.select()触发，从attachment获取到reader继续读取，直到读取完毕
		byte[] data = reader.getData();
		if (data == null) {
			return;
		}
		
		this.processor(channel, data);
	}
	
	private void processor(SocketChannel channel, byte[] data) throws IOException {
		System.out.println(ByteUtil.toString(data));
		this.write(channel, StringUtil.toBytes("success"));
	}
	
	private void write(SocketChannel channel, byte[] data) throws IOException {
		channel.register(this.selector, SelectionKey.OP_WRITE);
		data = ArrayUtil.merge(ByteUtil.int2Byte(data.length), data);
		channel.write(ByteBuffer.wrap(data));
		channel.register(this.selector, SelectionKey.OP_READ);
//		channel.close();
	}
	
	private void close() {
		System.out.println("通道异常");
	}
	
	public static void main(String[] args) throws IOException {
		NIOServer server = new NIOServer();
		server.initServer(8888);
		server.listen();
	}
}
