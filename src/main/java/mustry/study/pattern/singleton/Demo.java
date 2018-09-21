package mustry.study.pattern.singleton;

class Bean {
	private Bean() {
	}

	/*
	 * 听说这个关键词可以防止jvm进行指令重排序，把开辟内存、将指针指向内存地址 放在 实例化对象前面
	 * 如果放在前面就可能会发生对象没有实例化的时候会被判断为不为空而使用
	 */
	private volatile static Bean bean;

	public static Bean getbean() {
		if (bean == null) {
			synchronized (Bean.class) {
				if (bean == null) {
//					Bean temp = new Bean();
//					bean = temp;
					bean = new Bean();
				}
			}
		}
		return bean;
	}
}

public class Demo {
	public static void main(String[] args) {
		for(int i = 0;i<100;i++) {
			System.out.println(Bean.getbean());
		}
	}
}