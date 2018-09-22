package ggboy.study.java.serializable;

import java.io.UnsupportedEncodingException;

import ggboy.study.java.common.utils.serialize.SerializableUtil;
import mustry.common.utils.base64.Base64Util;

public class Main {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		DemoInfo info = new DemoInfo(new DemoSon("序列化测试"));
//		System.out.println(info.getName());
//		byte[] bytes = SerializableUtil.serialize(info);
//		System.out.println(Base64Util.encodeToString(bytes));
		
		String data = "rO0ABXNyACJtdXN0cnkuc3R1ZHkuc2VyaWFsaXphYmxlLkRlbW9JbmZvAAAAAAAAAAECAAJMAAhkZW1vSW5mb3QAJExtdXN0cnkvc3R1ZHkvc2VyaWFsaXphYmxlL0RlbW9JbmZvO0wABG5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cHNyACFtdXN0cnkuc3R1ZHkuc2VyaWFsaXphYmxlLkRlbW9Tb25ahkw8gx+Y6gIAAHhxAH4AAHEAfgAFdAAP5bqP5YiX5YyW5rWL6K+VcA==";
		byte[] bytes = Base64Util.decode(data);
		DemoInfo info = SerializableUtil.reverseSerialize(bytes,DemoInfo.class);
		System.out.println(info.getName());
	}
	
	
	/*
	 * 
	 * 这个demo测试了当类序列化之后，将demoSon类删除，进行反序列化会报ClassNotFoundException异常
	 * 子类转换为父类其内在还是子类，所以应该在classload.forname的时候无法找到包 
	 * 
	 */
}