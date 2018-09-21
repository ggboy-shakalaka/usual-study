package mustry.study.springAction.action;

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
