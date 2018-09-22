package ggboy.idea.java.cxc.多线程监控回调;

import java.util.Random;

public class Demo4 {

	public static void main(String[] args) {
		int num = 0;
		int cishu = 0;
		Random random = new Random();
		for (int i = 0; i < 100000; i++) {
			int number = random.nextInt(10000);
			if (i > 0 && number == num) {
				System.out.println(++cishu);
			}
			num = number;
		}
	}
}
