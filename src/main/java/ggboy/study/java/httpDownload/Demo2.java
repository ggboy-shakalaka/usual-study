package ggboy.study.java.httpDownload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo2 {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("C:/Users/A/Desktop/temp/小视频/success.mp4");
		byte[] buffer = new byte[1024];
		fis.read(buffer);
		for (byte item : buffer) {
			System.out.println(item);
		}
	}
}
