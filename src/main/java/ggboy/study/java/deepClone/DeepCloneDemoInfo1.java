package ggboy.study.java.deepClone;

import java.io.Serializable;

public class DeepCloneDemoInfo1 implements Serializable {
	private static final long serialVersionUID = 1L;
	private DeepCloneDemoInfo2 info2 = new DeepCloneDemoInfo2();

	public DeepCloneDemoInfo2 getInfo2() {
		return info2;
	}

	public void setInfo2(DeepCloneDemoInfo2 info2) {
		this.info2 = info2;
	}
}
