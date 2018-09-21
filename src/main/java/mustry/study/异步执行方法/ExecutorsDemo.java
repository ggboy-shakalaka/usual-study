package mustry.study.异步执行方法;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ExecutorsDemo {
	public static void main(String[] args) throws Exception {
		ExecutorsDemo demo = new ExecutorsDemo();
		demo.demo2();
	}
	
	// 线程池调用
	public void demo1() throws Exception {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		Future<String> submit = newFixedThreadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("优先执行");
				Thread.sleep(5000);
				System.out.println("睡眠后执行");
				return "执行结果:success";
			}
		});
		System.out.println("方法执行中，异步调用后");
		System.out.println(submit.get());
	}
	
	// 基本调用
	public void demo2() throws Exception {
		Callable<String> callable = new Callable<String>() { 
			@Override 
			public String call() throws Exception {
				System.out.println("优先执行");
				Thread.sleep(5000);
				System.out.println("睡眠后执行");
				return "执行结果:success";
			}
		};
		FutureTask<String> future = new FutureTask<String>(callable);
		new Thread(future).start();
		System.out.println("方法执行中，异步调用后");
		System.out.println(future.get());
	}
}
