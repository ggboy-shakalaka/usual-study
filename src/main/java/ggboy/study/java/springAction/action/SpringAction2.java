package ggboy.study.java.springAction.action;

import org.springframework.stereotype.Component;

@Component
public class SpringAction2 implements Action {
	
	public SpringAction2() {
		System.out.println(this.getClass().getSimpleName() + "is initialization...");
	}

	@Override
	public void action(String str) {
		
	}
}
