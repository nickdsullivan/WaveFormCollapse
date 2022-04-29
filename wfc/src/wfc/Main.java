package wfc;
import java.awt.color.*;
enum types{
	unknown,grass,lightForest,sand,shore,deepWater
}

public class Main {
	public static int width = 500;
	public static int height = 500;
	public static void main(String[] args) {
		GameFrames gf = new GameFrames();
		//This is why I hate oop need to pass object through each other.
		Grid grid = new Grid(10,10,gf);
		grid.step();
		
		

	}

}
