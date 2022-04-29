package wfc;

import java.awt.Color;
//Didn't want to do the inheirdance because no need only 2 shapes would result in failure.  
public class myShape {
	String type;
	int x1;
	int y1;
	int x2;
	int y2;
	int size;
	Color color;
	//For non lines.  
	
	public myShape(String type, int x, int y, int size) {
		this.x1 = x+1;
		this.y1 = y+1;
		this.size = size-2;
		this.type = type;
		color = new Color(255,255,255);
		
	}
	public myShape(String type, int x1, int y1, int x2, int y2) {
		this.type = type;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		 color = new Color(0,0,0);
	}
	public void setColor(int r, int g, int b) {
		//Just make a new color java's garb collection will deal with this
		//Probably just a color pool anyway so who cares if cause it will be
		// a common color.
		this.color = new Color(r,g,b);
	}
	public void setColor(Color c) {
		this.color = c;
	}
	
}
