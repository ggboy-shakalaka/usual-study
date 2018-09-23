package ggboy.study.java.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo {
	public static void main(String[] args) {
		DemoInterface demo = ProxyHandler.applyProxy(new DemoImplements());
		DemoInterface demo2 = ProxyHandler.applyProxy(DemoInterface.class);
		System.out.println("--------invoke demo1------------");
		System.out.println(demo.action());
		System.out.println("--------invoke demo2------------");
		System.out.println(demo2.action());
	}
}

interface DemoInterface {
	public String action();
}

class DemoImplements implements DemoInterface {
	@Override
	public String action() {
		System.out.println("in action method...");
		return "action method return message";
	}
}

class ProxyHandler {
	@SuppressWarnings("unchecked")
	// aop模式
	public static <T> T applyProxy(final T obj) {
		return (T) Proxy.newProxyInstance(ProxyHandler.class.getClassLoader(), obj.getClass().getInterfaces(),
				(Object proxy, Method method, Object[] args) -> {
					System.out.println("before invoke");
					Object result = method.invoke(obj, args);
					System.out.println("after invoke");
					return result;
				});
	}
	
	@SuppressWarnings("unchecked")
	// 直接代理接口模式
	public static <T> T applyProxy(Class<T> clazz) {
		return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {clazz},
				(Object proxy, Method method, Object[] args) -> {
					System.out.println("invoke");
					return "success";
				});
	}
}
