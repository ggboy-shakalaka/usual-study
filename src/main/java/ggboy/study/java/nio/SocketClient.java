package ggboy.study.java.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ggboy.java.common.exception.CommonUtilException;
import ggboy.java.common.utils.array.ArrayUtil;
import ggboy.java.common.utils.bytee.ByteUtil;
import ggboy.java.common.utils.io.IoUtil;
import ggboy.java.common.utils.string.StringUtil;

public class SocketClient {

	private Socket socket;
	private OutputStream out;
	private InputStream in;

	public SocketClient() throws UnknownHostException, IOException {
		socket = new Socket("127.0.0.1", 8888);
		out = this.socket.getOutputStream();
		in = this.socket.getInputStream();
	}

	public void write(byte[] data) throws IOException {
		this.out.write(data);
	}

	public byte[] read() throws CommonUtilException {
		return IoUtil.in2BytesByHeadWithDataLength(this.in);
	}

	public static void main(String[] args) throws Exception {
		SocketClient client = new SocketClient();
		while (true) {
			byte[] data = StringUtil.toBytes("check");
			byte[] head = ByteUtil.int2Byte(data.length);
			byte[] baseData = ArrayUtil.merge(head, data);
			client.write(baseData);
			System.out.println(ByteUtil.toString(client.read()));
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
