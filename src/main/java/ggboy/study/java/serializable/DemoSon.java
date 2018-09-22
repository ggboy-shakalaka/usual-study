package ggboy.study.java.serializable;

public class DemoSon extends DemoInfo {
	private static final long serialVersionUID = 1L;

	public DemoSon(DemoInfo info) {
		super(info);
	}
	
	public DemoSon(String name) {
		super(name);
	}
}