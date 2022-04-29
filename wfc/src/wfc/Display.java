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
	ArrayList<myShape> shapes = new ArrayList<myShape>();
	Color currentColor;
	Display(){
	   	this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(Main.width,Main.height));
		this.setFocusable(true);
	}
	public void paint(Graphics g){
		for (myShape shape: shapes) {
			currentColor = g.getColor();
			switch(shape.type) {
			
			case("Line"):
				
				g.setColor(shape.color);
				g.drawLine(shape.x1, shape.y1, shape.x2, shape.y2);
				break;
			case("Square"):
				g.setColor(shape.color);
				
				g.fillRect(shape.x1, shape.y1, shape.size, shape.size);
				break;
			default:
				System.out.println("Undetected Shape: " +shape.type);
				break;
			}
			
		}
		g.setColor(currentColor);
		repaint();
		
	}
	public void addShape(myShape shape) {
		shapes.add(shape);
	}

	

	
}