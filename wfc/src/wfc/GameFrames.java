package wfc;


import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameFrames extends JFrame {
	public Display a;
    GameFrames() {
        this.a  = new Display();
        this.add(a);
        this.setTitle("Blobs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
       
    }
    public Display getA() {
 
    	return a;
    }
    
}

