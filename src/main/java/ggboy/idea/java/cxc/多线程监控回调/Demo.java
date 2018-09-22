package ggboy.idea.java.cxc.多线程监控回调;

public class Demo {
	public static void main(String[] args) {
		回调器 callback = new Callback();

		int threadSize = 68;

		监控器 watcher = new 监控器(threadSize, callback);

		for (int i = 0; i < threadSize/2; i++) {
			new Executer(watcher).start();
			new Executer2(watcher).start();
		}
	}
}

class Executer extends 执行器 {
	Executer(监控器 watcher) {
		super(watcher);
	}

	@Override
	public void action() {
		System.out.println(this.getName() + " is run");
	}
}

class Executer2 extends 执行器 {
	Executer2(监控器 watcher) {
		super(watcher);
	}

	@Override
	public void action() {
		System.out.println(this.getName() + " is run........");
	}
}

class Callback implements 回调器 {
	@Override
	public void doAfter() {
		System.out.println("It is all done");
	}
}