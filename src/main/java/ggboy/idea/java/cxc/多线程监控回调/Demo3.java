package ggboy.idea.java.cxc.多线程监控回调;

import java.util.concurrent.CountDownLatch;

public class Demo3 {
	static CountDownLatch cdl = new CountDownLatch(4);
	
	public static void main(String[] args) {
		Task3 t1 = new Task3();
		Task3 t2 = new Task3();
		Task4 t3 = new Task4();
		Task4 t4 = new Task4();
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		System.out.println("asd");
		
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("finish");
		
	}
}

class Task3 extends Thread {
	public void run() {
		System.out.println(this.getName() + " start");
		// do something
		System.out.println(this.getName() + " end");
		
		Demo3.cdl.countDown();
	}
}

class Task4 extends Thread {
	public void run() {
		System.out.println(this.getName() + " start");
		
		// do something
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(this.getName() + " end");
		Demo3.cdl.countDown();
	}
}
