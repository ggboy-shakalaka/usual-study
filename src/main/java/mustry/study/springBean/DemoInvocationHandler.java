package mustry.study.springBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DemoInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy is effective...");
		return null;
	}
}