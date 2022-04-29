package wfc;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Display extends JPanel{
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	Display(){
	   	this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(Main.width,Main.height));
		this.setFocusable(true);
	}
	public void paint(Graphics g){
		
		repaint();
		
	}

	

	
}