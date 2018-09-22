package ggboy.idea.java.cxc.createNewFileByString;

import java.io.ByteArrayInputStream;

public class Demo {
	public static void main(String[] args) throws Exception {
		byte[] data = "呀哈哈...哒哒哒```".getBytes("GBK");
		ByteArrayInputStream is = new ByteArrayInputStream(data);
//		FileInputStream fis = new FileInputStream("C:/Users/A/Desktop/demo/123.txt");
	}
}
