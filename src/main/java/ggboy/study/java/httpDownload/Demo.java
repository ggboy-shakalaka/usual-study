package ggboy.study.java.httpDownload;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import mustry.common.download.impl.FileDownloadImpl;
import mustry.common.http.SimpleHttpClient;

public class Demo {
	public static void main(String[] args) throws Exception {
		SimpleHttpClient client = SimpleHttpClient.getClient();
		HttpResponse resp = client.get("https://dldir1.qq.com/qqfile/qq/TIM2.2.0/23734/TIM2.2.0.exe");
		long length = resp.getEntity().getContentLength();
		if (length <= 0) {
			for (Header head : resp.getAllHeaders()) {
				System.out.println(head.getName() + " : " + head.getValue());
			}
			return;
		}

		FileDownloadImpl fileDownload = new FileDownloadImpl("C:/Users/A/Desktop/temp/小视频/temp", "success.exe", length);
		fileDownload.addTask(resp.getEntity().getContent());

		long size = 0;
		while (true) {
			size = fileDownload.schedule();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (fileDownload.schedule() < 0)
				break;
			
			System.out.println("当前速度 : " + (fileDownload.schedule() - size) / 1024 + "kb/s,完成进度 : "
					+ (fileDownload.schedule() * 100 / length) + "%");
		}
	}
}