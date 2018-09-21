package mustry.study.deepClone;

import java.io.Serializable;

public class DeepCloneDemoInfo3 implements Serializable {
	private static final long serialVersionUID = 1L;
	private DeepCloneDemoInfo4 info4 = new DeepCloneDemoInfo4();

	public DeepCloneDemoInfo4 getInfo4() {
		return info4;
	}

	public void setInfo4(DeepCloneDemoInfo4 info4) {
		this.info4 = info4;
	}
}
