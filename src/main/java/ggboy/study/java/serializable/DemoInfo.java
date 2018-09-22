package ggboy.study.java.serializable;

import java.io.Serializable;

public class DemoInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private DemoInfo demoInfo = this;
	
	public DemoInfo(DemoInfo info) {
		this.demoInfo = info;
	}
	
	public DemoInfo(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.demoInfo.name;
	}
	public void setName(String name) {
		this.demoInfo.name = name;
	}
}
