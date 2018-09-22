package ggboy.study.java.springAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DothingAop {
	private static final String POINTCUT = "execution(* mustry.study.springAop.*.*(..))";
	
	DothingAop() {
		System.out.println("asd");
		System.out.println("123");
	}
	
	@Before(POINTCUT)
	public void before(JoinPoint point) {
		System.out.println("Aop before...");
	}
	
	@After(POINTCUT)
	public void after(JoinPoint point) {
		System.out.println("Aop after...");
	}
}