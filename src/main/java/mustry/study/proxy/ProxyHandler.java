package mustry.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler {
	
	@SuppressWarnings("unchecked")
	public static <T> T applyProxy(final T obj) {
		return (T) Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("before");
				Object result = method.invoke(obj, args);
				System.out.println("after");
				return result;
			}
		}); 
	}
}