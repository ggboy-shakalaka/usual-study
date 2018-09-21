package mustry.study2.majiang;

public class Tile {
	private MahjongTypeEnum type;
	private int num;
	
	public Tile(MahjongTypeEnum type, int num) {
		if (type == null || num < 0 || num >= type.getSize())
			throw new IllegalArgumentException();
		this.type = type;
		this.num = num;
	}

	public boolean equalsType(Tile tile) {
		return tile != null && this.type == tile.type;
	}

	public boolean equalsNum(Tile tile) {
		return tile != null && this.num == tile.num;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Tile))
			return false;

		Tile t = (Tile) obj;

		return equalsType(t) && equalsNum(t);
	}
	
	public int getNum() {
		return this.num;
	}
	
	public String toString() {
		return type.name() + " - " + num;
	}
}