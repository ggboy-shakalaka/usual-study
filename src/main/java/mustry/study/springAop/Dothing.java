package mustry.study.springAop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Dothing {
	public Dothing() {
		System.out.println("init");
	}
	
	public void action() {
		System.out.println("do thing...");
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/context.xml");
		Dothing dothing = (Dothing) context.getBean("dothing");
		
		dothing.action();
	}
}
