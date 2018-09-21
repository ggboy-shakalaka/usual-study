package mustry.study.test;

import java.io.IOException;
import java.net.ServerSocket;

public class Test2 {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("lision");
		while (true) {
			serverSocket.accept();
		}
	}
}
