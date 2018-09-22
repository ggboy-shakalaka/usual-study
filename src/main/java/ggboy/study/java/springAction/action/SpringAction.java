package ggboy.study.java.springAction.action;

import org.springframework.stereotype.Component;

@Component
public class SpringAction implements Action {
	
	public SpringAction() {
		System.out.println(this.getClass().getSimpleName() + "is initialization...");
	}

	@Override
	public void action(String str) {
		
	}
}
