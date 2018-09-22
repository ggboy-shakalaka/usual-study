package ggboy.study.java.springBeanReq;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

/*
 * 此类继承工厂bean,在springIoc容器注入的时候,会调用此类的getObject方法,将或得到的返回值注入到Ioc容器中
 * 而不是将此类的实例注入springIoc容器
 */
public class BeanProxyFactory<T> implements FactoryBean<T> {

	private Class<T> clazz;
	private InvocationHandler invocation;
	private boolean singleton;

	public BeanProxyFactory(Class<T> clazz, InvocationHandler invocation) {
		this(clazz, invocation, true);
	}

	public BeanProxyFactory(Class<T> clazz, InvocationHandler invocation, boolean singleton) {
		this.clazz = clazz;
		this.invocation = invocation;
		this.singleton = singleton;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getObject() throws Exception {
		// 用java提供的代理方法将代理作为实现类注入到springIoc容器中
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { clazz }, invocation);
	}

	@Override
	public Class<?> getObjectType() {
		return clazz;
	}

	@Override
	public boolean isSingleton() {
		return singleton;
	}

}