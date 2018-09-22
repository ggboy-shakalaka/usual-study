package ggboy.study.java.异步执行方法;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ExecutorsDemo {
	public static void main(String[] args) {
		try {
			ExecutorsDemo.demo1();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	// 线程池调用
	public final static void demo1() throws InterruptedException, ExecutionException {
		var submit = Executors.newFixedThreadPool(1).submit(() -> {
			System.out.println("优先执行");
			Thread.sleep(5000);
			System.out.println("睡眠后执行");
			return "执行结果:success";
		});
		System.out.println("方法执行中，异步调用后");
		System.out.println(submit.get());
	}

	// 基本调用
	public final static void demo2() throws InterruptedException, ExecutionException {
		var future = new FutureTask<String>(() -> {
			System.out.println("优先执行");
			Thread.sleep(5000);
			System.out.println("睡眠后执行");
			return "执行结果:success";
		});
		new Thread(future).start();
		System.out.println("方法执行中，异步调用后");
		System.out.println(future.get());
	}
}
