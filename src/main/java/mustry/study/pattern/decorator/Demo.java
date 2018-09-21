package mustry.study.pattern.decorator;

public class Demo {
	public static void main(String[] args) {
		Man man = new Man();
		man.walk();
		man.fly();
		System.out.println("开启装饰器模式。。。");
		man = new SuperMan(man);
		man.walk();
		man.fly();
	}
}

class Man {
	public void walk() {
		System.out.println("he is walking...");
	}
	public void fly() {
		System.out.println("he cann't fly...");
	}
}

class SuperMan extends Man {
	private final Man man;
	public SuperMan(Man man) {
		this.man = man;
	}
	
	@Override
	public void walk() {
		this.man.walk();
		System.out.println("he walk very fast...");
	}
	
	@Override
	public void fly() {
		this.man.fly();
		System.out.println("he has power...");
		System.out.println("he can fly...");
	}
}