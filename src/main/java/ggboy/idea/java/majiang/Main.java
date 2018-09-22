package ggboy.idea.java.majiang;

public class Main {
	public static void main(String[] args) {
		// build Tiles
		Tile[] tiles = TilesUtil.buildTiles();

		// wash tiles
		tiles = TilesUtil.washTiles(tiles);
		
		for (Tile item : tiles)
			System.out.println(item);

	}
}