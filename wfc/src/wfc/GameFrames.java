package wfc;


import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameFrames extends JFrame {
	public Display display;
    GameFrames() {
        this.display  = new Display();
        this.add(display);
        this.setTitle("Blobs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
       
    }
    public Display getDisplay() {
 
    	return display;
    }
    
}

