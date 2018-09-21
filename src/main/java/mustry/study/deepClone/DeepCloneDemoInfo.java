package mustry.study.deepClone;

import java.io.Serializable;

public class DeepCloneDemoInfo implements Cloneable,Serializable {
	private static final long serialVersionUID = 1L;
	private DeepCloneDemoInfo1 info1 = new DeepCloneDemoInfo1();
	
	public DeepCloneDemoInfo1 getInfo1() {
		return info1;
	}

	public void setInfo1(DeepCloneDemoInfo1 info1) {
		this.info1 = info1;
	}
	
	@Override
	public DeepCloneDemoInfo clone() {
		try {
			return (DeepCloneDemoInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
