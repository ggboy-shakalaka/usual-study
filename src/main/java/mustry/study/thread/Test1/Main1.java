package mustry.study.thread.Test1;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class Main1 {
	private static List<String> a = new ArrayList<String>();
	private static List<String> b = new ArrayList<String>();
	private static List<String> c = new ArrayList<String>();
	private static List<String> d = new ArrayList<String>();
	public static int flag = 0;
	
	public static void main(String[] args) {
		List<List<String>> base = new ArrayList<List<String>>();
		base.add(a);
		base.add(b);
		base.add(c);
		base.add(d);
		Main1 main = new Main1();
		main.threadA(base);
		main.threadB(base);
		main.threadC(base);
		main.threadD(base);
		while(true) {
			if(flag==4) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println("A : " + JSON.toJSONString(a));
		System.out.println("B : " + JSON.toJSONString(b));
		System.out.println("C : " + JSON.toJSONString(c));
		System.out.println("D : " + JSON.toJSONString(d));
	}
	
	public void threadA(final List<List<String>> fileList) {
		new Thread(new Runnable() { @Override public void run() {
			int n = 0;
			while(n<10) {
				List<String> oneFile = fileList.get(n%4);
				synchronized (oneFile) {
					oneFile.add("a");
				}
				n++;
			}
			Main1.addFlag();
		}}).start();
	}
	private void threadB(final List<List<String>> fileList) {
		new Thread(new Runnable() { @Override public void run() {
			int n = 0;
			while(n<10) {
				List<String> oneFile = fileList.get(n%4);
				synchronized (oneFile) {
					oneFile.add("b");
				}
				n++;
			}
			Main1.addFlag();
		}}).start();
	}
	private void threadC(final List<List<String>> fileList) {
		new Thread(new Runnable() { @Override public void run() {
			int n = 0;
			while(n<10) {
				List<String> oneFile = fileList.get(n%4);
				synchronized (oneFile) {
					oneFile.add("c");
				}
				n++;
			}
			Main1.addFlag();
		}}).start();
	}
	private void threadD(final List<List<String>> fileList) {
		new Thread(new Runnable() { @Override public void run() {
			int n = 0;
			while(n<10) {
				List<String> oneFile = fileList.get(n%4);
				synchronized (oneFile) {
					oneFile.add("d");
				}
				n++;
			}
			Main1.addFlag();
		}}).start();
	}
	
	public static void addFlag() {
		synchronized (Main1.class) {
			Main1.flag++;
		}
	}
}
