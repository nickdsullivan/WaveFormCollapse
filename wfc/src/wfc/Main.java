package wfc;
import java.awt.color.*;
enum types{
	unknown,grass,lightForest,sand,shore,deepWater
}

public class Main {
	public static int width = 1200;
	public static int height = 1200;
	public static void main(String[] args) {
		GameFrames gf = new GameFrames();
		//This is why I hate oop need to pass object through each other.
		Grid grid = new Grid(200,200,gf);
		
		while(grid.step()) {
			
			try {
				Thread.sleep(0);
			}
			catch(Exception e) {
				
			}
		}
		
	}

}
