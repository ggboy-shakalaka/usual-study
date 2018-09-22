package ggboy.study.java.proxy;

public interface Demo {
	public void action();
	public String action2();
}

class Demo1 implements Demo{

	@Override
	public void action() {
		System.out.println("action");
	}

	@Override
	public String action2() {
		System.out.println("action2");
		return "action2 return method";
	}
	
}