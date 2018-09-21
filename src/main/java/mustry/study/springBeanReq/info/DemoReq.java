package mustry.study.springBeanReq.info;

import java.io.Serializable;

public class DemoReq implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Class<?> clazz;
	private final String method;
	private final Class<?>[] parameterTypes;
	private final Object[] parameters;
	
	public DemoReq(Class<?> clazz,String method,Class<?>[] parameterTypes,Object[] parameters) {
		this.clazz = clazz;
		this.method = method;
		this.parameterTypes = parameterTypes;
		this.parameters = parameters;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getMethod() {
		return method;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public Object[] getParameters() {
		return parameters;
	}
}