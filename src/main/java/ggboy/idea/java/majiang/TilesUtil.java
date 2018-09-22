package ggboy.idea.java.majiang;

import java.util.Random;

public class TilesUtil {
	public final static int getTilesSize() {
		int size = 0;
		for (MahjongTypeEnum item : MahjongTypeEnum.values()) {
			size += item.getSize() * 4;
		}
		return size;
	}

	public final static Tile[] buildTiles() {
		Tile[] tiles = new Tile[getTilesSize()];

		int index = 0;
		for (MahjongTypeEnum item : MahjongTypeEnum.values())
			for (int i = 0; i < item.getSize(); i++)
				for (int j = 0; j < 4; j++)
					tiles[index++] = new Tile(item, i);

		return tiles;
	}

	public final static Tile[] washTiles(Tile[] tiles) {
		Random random = new Random();

		for (int i = 0; i < tiles.length; i++) {
			int index = random.nextInt(tiles.length);
			Tile tile = tiles[i];
			tiles[i] = tiles[index];
			tiles[index] = tile;
		}

		return tiles;
	}

	// 判断是否为顺子
	public final static boolean isSequence(Tile t1, Tile t2, Tile t3) {
		if (t1 == null)
			return false;

		if (t1.equalsType(t2) || t1.equalsType(t3))
			return false;

		if (isTriplet(t1, t2, t3))
			return false;

		return min(t1.getNum(), t2.getNum(), t3.getNum()) + 2 == max(t1.getNum(), t2.getNum(), t3.getNum());
	}

	// 判断是否为刻子
	public final static boolean isTriplet(Tile t1, Tile t2, Tile t3) {
		if (t1 == null)
			return false;
		return t1.equalsNum(t2) && t1.equalsNum(t2) && t2.equalsNum(t3);
	}

	private final static int max(int... nums) {
		if (nums == null || nums.length < 1)
			throw new IllegalArgumentException();

		int num = Integer.MIN_VALUE;

		for (int item : nums)
			num = item > num ? item : num;

		return num;
	}

	private final static int min(int... nums) {
		if (nums == null || nums.length < 1)
			throw new IllegalArgumentException();

		int num = Integer.MAX_VALUE;

		for (int item : nums)
			num = item < num ? item : num;

		return num;
	}
}
