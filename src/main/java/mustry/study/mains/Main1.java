package mustry.study.mains;

public class Main1 {
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.i = 123;
		System.out.println("方法外 : " + demo.hashCode());
		change(demo);
		System.out.println(demo.i);
		change2(demo);
		System.out.println(demo.i);
	}
	
	public static void change(Demo data) {
		System.out.println("方法内前 : " + data.hashCode());
		data = new Demo();
		System.out.println("方法内后 : " + data.hashCode());
		data.i = 321;
	}
	
	 public static void change2(Demo data) {
		data.i = 321;
		System.out.println("方法内 : " + data.hashCode());
	}
}

class Demo {
	public int i;
}