package ggboy.study.java.springBeanReq;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import ggboy.java.common.http.SimpleHttpClient;
import ggboy.java.common.utils.serialize.SerializableUtil;
import ggboy.study.java.springBeanReq.info.DemoReq;

public class DemoReqInvocationHandler implements InvocationHandler {

	private final Class<?> clazz;

	public DemoReqInvocationHandler(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy is effective...");
		String url = "";
		String service = clazz.getAnnotation(Remote.class).service();
		DemoReq req = new DemoReq(clazz, method.getName(), method.getParameterTypes(), args);

		if ("demo".equals(service)) {
			url = "http://127.0.0.1/study/getthing.html";
		}

//		HttpResponse resp = SimpleHttpClient.getClient().get(url, SerializableUtil.serialize(req));
		HttpResponse resp = null;
		return SerializableUtil.reverseSerialize(EntityUtils.toByteArray(resp.getEntity()), method.getReturnType());
	}
}