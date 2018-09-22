package ggboy.study.java.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import ggboy.java.common.constant.IOConstant;
import ggboy.java.common.enums.ErrorCode;
import ggboy.java.common.exception.DeepException;

/**
 * 非线程安全，此类切记不可为静态，切记切记
 * @author switch2018@outlook.com
 */
public class NioReader implements BufferReader {
	private static final int HEAD_SIZE = 4;
	private ByteBuffer headBuffer = ByteBuffer.allocate(HEAD_SIZE);
	private ByteBuffer bodyBuffer;

	public int doRead(SocketChannel channel) {
		int count = -1;
		try {
			if (headBuffer.remaining() > 0) {
				count = channel.read(headBuffer);
				if (headBuffer.remaining() > 0) {
					return count;
				}
			}
			
			if (bodyBuffer == null) {
				headBuffer.flip();
				int dataLength = headBuffer.getInt();
				if (dataLength < 0) {
					throw new DeepException(ErrorCode.system_error);
				}
				bodyBuffer = ByteBuffer.allocate(dataLength);
			}
			
			if (bodyBuffer.remaining() > 0) {
				count = read(channel, bodyBuffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	private static int read (SocketChannel channel, ByteBuffer buffer) throws IOException {
		return buffer.remaining() > IOConstant.default_buffer_length ? customRead(channel, buffer) : channel.read(buffer);
	}
	
	private static int customRead (SocketChannel channel, ByteBuffer buffer) throws IOException {
		int count = -1;
		int baseLimit = buffer.limit();
		try {
			while (buffer.remaining() > 0) {
				int readLength = Math.min(buffer.remaining(), IOConstant.default_buffer_length);
				buffer.limit(buffer.position() + readLength);
				count = channel.read(buffer);
				if (buffer.remaining() > 0) {
					return count;
				}
				buffer.limit(baseLimit);
			}
			return count;
		} finally {
			buffer.limit(baseLimit);
		}
	}

	/**
	 * 非线程安全，注意使用
	 */
	@Override
	public byte[] getData() {
		if (this.bodyBuffer != null && this.bodyBuffer.remaining() == 0) {
			try {
				return bodyBuffer.array();
			} finally {
				this.clear();
			}
		}
		return null;
	}

	@Override
	public void clear() {
		if (headBuffer != null) {
			headBuffer.clear();
		}
		if (bodyBuffer != null) {
			bodyBuffer.clear();
			bodyBuffer = null;
		}
		
	}
}
