package ggboy.idea.java.cxc.多线程监控回调;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Demo2 {
	static CyclicBarrier cb = new CyclicBarrier(4);
	
	
	
	public static void main(String[] args) {
		Task1 t1 = new Task1();
		Task1 t2 = new Task1();
		Task2 t3 = new Task2();
		Task2 t4 = new Task2();
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class Task1 extends Thread {
	public void run() {
		System.out.println(this.getName() + " start");
		// do something
		System.out.println(this.getName() + " end");
		
		
		try {
			Demo2.cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(this.getName() + " done");
		
	}
}

class Task2 extends Thread {
	public void run() {
		System.out.println(this.getName() + " start");
		
		// do something
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(this.getName() + " end");
		try {
			Demo2.cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.println(this.getName() + " done");
	}
}