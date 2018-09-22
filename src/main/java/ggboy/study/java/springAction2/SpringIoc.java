package ggboy.study.java.springAction2;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringIoc {
	@Autowired
	private ApplicationContext context;
	
	@PostConstruct
	public void action() {
		for(String beanName : context.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
	}
}
