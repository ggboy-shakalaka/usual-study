package ggboy.study.java.threadPool;

import mustry.common.thread.pool.ThreadPool;

public class Test {
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPool();
		for (int i = 0; i < 20; i++) {
			pool.push(new Runnable() { public void run() {
				for (int i = 0; i < 2; i++) {
					System.out.println("线程 : " + this.hashCode() + " 运行了" + i + " 次");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}});
		}
	}
}
