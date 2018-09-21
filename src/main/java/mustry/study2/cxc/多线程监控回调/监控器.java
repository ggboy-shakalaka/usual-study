package mustry.study2.cxc.多线程监控回调;

public class 监控器 {
	private int num;
	private int finish;
	private 回调器 callback;
	
	public 监控器(int num, 回调器 callback) {
		this.num = num;
		this.finish = 0;
		this.callback = callback;
	}
	
	public void finish() {
		this.finish++;
		if (this.finish == this.num) {
			callback.doAfter();
		}
	}
}

interface 回调器 {
	void doAfter();
}

abstract class 执行器 extends Thread {
	private 监控器 watcher;

	执行器(监控器 watcher) {
		this.watcher = watcher;
	}
	
	public void run() {
		action();
		watcher.finish();
	}
	
	public abstract void action();
}