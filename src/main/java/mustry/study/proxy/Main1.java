package mustry.study.proxy;

public class Main1 {
	public static void main(String[] args) {
		Demo demo = ProxyHandler.applyProxy(new Demo1());
		System.out.println(demo.action2());
	}
}
