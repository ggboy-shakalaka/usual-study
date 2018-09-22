package ggboy.idea.java.majiang;

public enum MahjongTypeEnum {
	Character(9),			// 万
	Dot(9),					// 筒
	Bamboo(9),				// 条
	Wind(4),				// 东西南北
	Dragon(3)				// 中发白
	;
	
	private int size;

	private MahjongTypeEnum(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
}
