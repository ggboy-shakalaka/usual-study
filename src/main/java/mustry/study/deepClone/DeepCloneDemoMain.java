package mustry.study.deepClone;

import mustry.study.common.utils.serialize.SerializableUtil;

public class DeepCloneDemoMain {
	public static void main(String[] args) {
		DeepCloneDemoInfo info = new DeepCloneDemoInfo();
		System.out.println(info);
		System.out.println(info.getInfo1());
		System.out.println(info.getInfo1().getInfo2());
		System.out.println(info.getInfo1().getInfo2().getInfo3());
		System.out.println(info.getInfo1().getInfo2().getInfo3().getInfo4());
		System.out.println(info.getInfo1().getInfo2().getInfo3().getInfo4().getInfo5());
		System.out.println(info.getInfo1().getInfo2().getInfo3().getInfo4().getInfo5().name);
		DeepCloneDemoInfo info2 = info.clone();
		System.out.println(info2);
		System.out.println(info2.getInfo1());
		System.out.println(info2.getInfo1().getInfo2());
		System.out.println(info2.getInfo1().getInfo2().getInfo3());
		System.out.println(info2.getInfo1().getInfo2().getInfo3().getInfo4());
		System.out.println(info2.getInfo1().getInfo2().getInfo3().getInfo4().getInfo5());
		System.out.println(info2.getInfo1().getInfo2().getInfo3().getInfo4().getInfo5().name);
		byte[] data = SerializableUtil.serialize(info);
		DeepCloneDemoInfo info3 = SerializableUtil.reverseSerialize(data, DeepCloneDemoInfo.class);
		System.out.println(info3);
		System.out.println(info3.getInfo1());
		System.out.println(info3.getInfo1().getInfo2());
		System.out.println(info3.getInfo1().getInfo2().getInfo3());
		System.out.println(info3.getInfo1().getInfo2().getInfo3().getInfo4());
		System.out.println(info3.getInfo1().getInfo2().getInfo3().getInfo4().getInfo5());
		System.out.println(info3.getInfo1().getInfo2().getInfo3().getInfo4().getInfo5().name);
	}
}
