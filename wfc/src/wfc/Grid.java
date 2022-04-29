package wfc;

public class Grid {
	Tile [] [] tiles;
	int [] a;
	int w;
	int h;
	myShape [][] squares ;
	public Grid(int w, int h,GameFrames gf) {
		this.w = w;
		this.h = h;
		tiles = new Tile[w][h];
		squares = new myShape [w][h];
		//init tiles
		for (int col =0; col < tiles.length; col ++) {
			for (int row = 0; row < tiles[col].length; row++) {
				tiles[col][row] = new Tile();
			}
		}
		
		//Adds lines to the display
		for (int i =0; i < Main.width; i += Main.width/w) {
			gf.getDisplay().addShape(new myShape("Line", i, 0, i, Main.height));
		}
		for (int i =0; i < Main.height; i += Main.width/w) {
			gf.getDisplay().addShape(new myShape("Line", 0, i, Main.width, i));
		}
		//after this we can access squares and update the display because the pass by reference.
		for (int col =0; col < w; col ++) {
			for (int row = 0; row < h; row ++) {
				squares[col][row] = new myShape("Square",col*(Main.width/w),row*(Main.height/h),Main.width/w);
				gf.getDisplay().addShape(squares[col][row]);
				
			}
		}
			
	}
	public void step() {
		//Update Color
		tiles[0][0].collapse();
		for (int col = 0; col < this.w; col++) {
			for (int row = 0; row < this.h; row++) {
				squares[col][row].setColor(tiles[col][row].getColor());
			}
		}
		
	}
	
}
