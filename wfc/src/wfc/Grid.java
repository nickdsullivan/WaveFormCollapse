package wfc;

public class Grid {
	Tile [] [] tiles;
	int [] a;
	public Grid(int w, int h) {
		tiles = new Tile[w][h];
		//init tiles
		for (int col =0; col < tiles.length; col ++) {
			for (int row = 0; row < tiles[col].length; row++) {
				tiles[col][row] = new Tile();
			}
		}
		
		
		
	}
}
