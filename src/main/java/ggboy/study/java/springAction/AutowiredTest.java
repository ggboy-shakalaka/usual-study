package ggboy.study.java.springAction;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class AutowiredTest extends AutoSupport implements AutowiredLocal {

	public AutowiredTest() {
		System.out.println(this.getClass().getSimpleName() + "is initialization...");
	}
	
	@Override
	public void todoSomething() {
		System.out.println("do nothing...");
	}
	
	@PostConstruct
	public void startDothing() {
		System.out.println(this.getClass().getSimpleName() + " say : is up...");
	}
	
	@PreDestroy
	public void endDothing() {
		System.out.println(this.getClass().getSimpleName() + " say : is down...");
	}
}
