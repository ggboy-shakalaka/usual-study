package ggboy.study.java.deepClone;

import java.io.Serializable;

public class DeepCloneDemoInfo4 implements Serializable {
	private static final long serialVersionUID = 1L;
	private DeepCloneDemoInfo5 info5 = new DeepCloneDemoInfo5();

	public DeepCloneDemoInfo5 getInfo5() {
		return info5;
	}

	public void setInfo5(DeepCloneDemoInfo5 info5) {
		this.info5 = info5;
	}
}
