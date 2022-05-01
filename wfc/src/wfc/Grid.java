package wfc;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Grid {
	Tile [] [] tiles;
	int [] a;
	int w;
	int h;
	myShape [][] squares ;
	LinkedList <Tile> nextToCollapse;
	
	
	
	public Grid(int w, int h,GameFrames gf) {
		this.w = w;
		this.h = h;
		tiles = new Tile[w][h];
		squares = new myShape [w][h];
		nextToCollapse = new LinkedList<Tile>();
		//init tiles
		for (int col =0; col < tiles.length; col ++) {
			for (int row = 0; row < tiles[col].length; row++) {
				tiles[col][row] = new Tile(col,row);
			}
		}
		
		//Adds lines to the display
		for (int i =0; i < Main.width; i += Main.width/w) {
			//gf.getDisplay().addShape(new myShape("Line", i, 0, i, Main.height));
		}
		for (int i =0; i < Main.height; i += Main.width/w) {
			//gf.getDisplay().addShape(new myShape("Line", 0, i, Main.width, i));
		}
		//after this we can access squares and update the display because the pass by reference.
		for (int col =0; col < w; col ++) {
			for (int row = 0; row < h; row ++) {
				squares[col][row] = new myShape("Square",col*(Main.width/w),row*(Main.height/h),Main.width/w);
				gf.getDisplay().addShape(squares[col][row]);
				
			}
		}
			
	}
	public boolean isValid(int x, int y) {
		if (x <0 || x >= w) {
			return false;
		}
		if (y <0 || y>= h) {
			return false;
		}
		return true;
	}
	public boolean step() {
		
		//Update Color
		Tile newTile;
		if (nextToCollapse.isEmpty()) {
			newTile = tiles[(int)(Math.random() * w)][ (int)(Math.random() * h)];
			if(newTile.isCollapsed()) {
				System.out.println("Failed to randomly choose a non collapsed state");
				return true;
			}
			nextToCollapse.add(newTile);
		}
		Tile nextTile;
		
		nextTile = nextToCollapse.remove();
		
		if (nextTile.isCollapsed()) {
			System.out.println("Collapsed state in queue skipping something went wrong");
		}
		//System.out.print(nextTile);
		nextTile.collapse();
		int [][] neigh_cords = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,1},{-1,-1},{1,-1}};
		
		List<int[]> list = Arrays.asList(neigh_cords);
		Collections.shuffle(list); 
		list.toArray(neigh_cords);
		
		for (int i =0; i < 8; i ++) {
			
			if (isValid(nextTile.xCord+neigh_cords[i][1],nextTile.yCord + neigh_cords[i][0])) {
				
				newTile = tiles[nextTile.xCord+neigh_cords[i][1]][nextTile.yCord + neigh_cords[i][0]];
				if (!newTile.isCollapsed()) {
					newTile.update(nextTile.getCurrent(),1);
					if(!nextToCollapse.contains(newTile)) {
						nextToCollapse.add(newTile);
					}
				}
			}
		}
		
		for (int col = 0; col < this.w; col++) {
			for (int row = 0; row < this.h; row++) {
				squares[col][row].setColor(tiles[col][row].getColor());
			}
		}
		return !nextToCollapse.isEmpty();
		
	
		
	}
	
}
