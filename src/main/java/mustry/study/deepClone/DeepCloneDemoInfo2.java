package mustry.study.deepClone;

import java.io.Serializable;

public class DeepCloneDemoInfo2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private DeepCloneDemoInfo3 info3 = new DeepCloneDemoInfo3();

	public DeepCloneDemoInfo3 getInfo3() {
		return info3;
	}

	public void setInfo3(DeepCloneDemoInfo3 info3) {
		this.info3 = info3;
	}
}
