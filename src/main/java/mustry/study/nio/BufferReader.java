package mustry.study.nio;

import java.nio.channels.SocketChannel;

public interface BufferReader {
	int doRead(SocketChannel channel);
	byte[] getData();
	void clear();
}
