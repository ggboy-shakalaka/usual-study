package ggboy.study.java.deepClone;

import java.io.Serializable;

import ggboy.java.common.exception.CommonUtilException;
import ggboy.java.common.utils.bean.BeanUtil;

public class Demo {
	public static void main(String[] args) throws CommonUtilException, CloneNotSupportedException {
		Info info = new Info(new Info(new Info(new Info(new Info(null)))));
		System.out.println("原始 hash");
		System.out.println(info);
		System.out.println(info.getInfo());
		System.out.println(info.getInfo().getInfo());
		System.out.println(info.getInfo().getInfo().getInfo());
		System.out.println(info.getInfo().getInfo().getInfo().getInfo());
		System.out.println("克隆 hash");
		info = info.clone();
		System.out.println(info);
		System.out.println(info.getInfo());
		System.out.println(info.getInfo().getInfo());
		System.out.println(info.getInfo().getInfo().getInfo());
		System.out.println(info.getInfo().getInfo().getInfo().getInfo());
		info = BeanUtil.deepCloneBean(info);
		System.out.println("深克隆 hash");
		System.out.println(info);
		System.out.println(info.getInfo());
		System.out.println(info.getInfo().getInfo());
		System.out.println(info.getInfo().getInfo().getInfo());
		System.out.println(info.getInfo().getInfo().getInfo().getInfo());
	}
}

class Info implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private Info info;

	public Info(Info info) {
		this.info = info;
	}

	public Info getInfo() {
		return this.info;
	}

	@Override
	public Info clone() throws CloneNotSupportedException {
		return (Info) super.clone();
	}
}