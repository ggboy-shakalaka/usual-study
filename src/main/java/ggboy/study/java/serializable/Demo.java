package ggboy.study.java.serializable;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import ggboy.java.common.exception.CommonUtilException;
import ggboy.java.common.utils.base64.Base64Util;
import ggboy.java.common.utils.serialize.SerializableUtil;

public class Demo {
	public static void main(String[] args) throws UnsupportedEncodingException, CommonUtilException {
		Info info = new SonInfo("序列化测试");
		System.out.println(info.getName());
		byte[] bytes = SerializableUtil.serialize(info);
		String data = Base64Util.encodeToString(bytes);
		System.out.println(data);
		bytes = Base64Util.decode(data);
		info = SerializableUtil.reverseSerialize(bytes,Info.class);
		System.out.println(info.getName());
	}
	/*
	 * 序列化为byte数组后转为base64
	 * 保留base64
	 * 反序列化前删除SonInfo类
	 * 反序列化时抛出ClassNotFoundException
	 * 说明即便转为父类其本质还是为子类
	 */
}

class Info implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Info(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}

class SonInfo extends Info {

	private static final long serialVersionUID = 1L;

	public SonInfo(String name) {
		super(name);
	}
}