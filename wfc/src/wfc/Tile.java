package wfc;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Tile {
	types current;
	//This will have the type as key and probablity that is will show up as the value
	Map<types, Float> possible;
	//types [] possible;
	public int xCord;
	public int yCord;
	boolean isCollapsed = false;
	public  Tile(int x, int y) {
		xCord = x;
		yCord = y;
		current = types.unknown;
		possible =  new HashMap<types,Float>();
		//Equal prob for all but unknown types
		Float value = Float.valueOf(((float) 1/(types.values().length-1)));
		for(types t: types.values()) {
			 if (t != types.unknown) {
				 possible.put(t,value);
			 }		
			 else {
				 possible.put(t, Float.valueOf(0));
			 }
			
		}
	}
	public types getCurrent() {
		return current;
	}
	public void update(types t, int dist) {
		checkRule(possible,t, (inside,outside) ->{
			switch(outside) {
			case grass:
				switch(inside) {
				case grass:
					return (float).7;
				case lightForest:
					return (float).2;
				case sand:
					return (float).1;
				default:
					return -1;
					
				}
			case lightForest:
				switch(inside) {
				case lightForest:
					return (float).9;
				case grass:
					return (float).1;
				default:
					return -1;
				}
			case sand:
				switch(inside) {
					case shore:
						return (float).2;
					case deepWater:
						
						return (float)-1;
					case sand:
						return(float) .7;
					case grass:
						return (float).1;
					default:
						return -1;
				}
			case shore:
				switch(inside) {
				case shore:
					return(float) .6;
				case deepWater:
					return(float) .1;
				case sand:
					return(float) .2;
				default:
					return -1;
				}
			case deepWater:
				switch(inside){
				case deepWater:
					return (float) 1;
				case shore:
					return(float) -.5;
				case sand:
					return(float) -1;
				default:
					return -1;
				}
			default:
				return -1;
			}
			
		});
		
	}
	
	public boolean isCollapsed()
	{
		return isCollapsed;
	}
	//Here I collapse the possible states.
	
	//This is doing weighted picking
	//choose the smallest prob and add it to the floor and put it back in
	//Now choose a random number and find the smallest float that is bigger than it and thats the tile you select.
	//Works because floats always add to 1
	public void collapse() {
		isCollapsed=true;
		float floor =0;
		
		float min = 2;
		types currMin = types.unknown;
		//For the random stuff
		float max = 0;
		types currMax = types.unknown;
		Map<types,Boolean> keysDone = new HashMap<types,Boolean>();
		
		for (types t: possible.keySet()) {
			keysDone.put(t, false);
		}
		boolean flag = true;
		
		//this can be done better.  In particular the loop can be done better but oh well
		while(flag) {
			
			for (types t: possible.keySet()) {
				//find the min
				if( min > possible.get(t) && !keysDone.get(t)) {
					
					currMin = t;
					min = possible.get(t);
				}
				//Set Overall Max so we dont have to do it later
				if (max < possible.get(t)) {
					max = possible.get(t);
					currMax =t;
				}
			}
			
			//Update the floor remove the
			floor = floor + min;
			possible.remove(currMin);
			possible.put(currMin, floor);
			
			keysDone.remove(currMin);
			keysDone.put(currMin, true);
			min = 2;
			flag = false;
			for (types t: keysDone.keySet()) {
				if (keysDone.get(t) == false) {
					flag = true;
					
				}
			}
			
			
		}
		
		
		
		
		//picks the smallest value that is bigger than the random number
		float minURand = 2;
		types currMaxURand= currMax;
		float rand =(float) Math.random();
		
		
		for (types t: possible.keySet()) {
			
			if (rand < possible.get(t) && possible.get(t) < minURand) {
				currMaxURand =t;
				minURand = possible.get(t);
				
			}
		}
		this.current = currMaxURand;
		//update the possibles to 0 now
		
		Map <types, Float> temp = new HashMap<types,Float>();
		for (types b: possible.keySet()) {
			if (b != this.current) {
				
				temp.put(b, Float.valueOf(0));
			}
			else {
				
				temp.put(b, Float.valueOf(1));
			}
		}
		possible = temp;
		//System.out.println("Successfully collapsed Tile: (" + xCord + ", " + yCord +") to " + this.current);
	}
	//Get color according to type
	public Color getColor() {

		switch (current) {
		case unknown:
			return Color.gray;
		case grass:
			return Color.green;
		case lightForest:
			return new Color(1,50,32);
		case shore:
			return new Color(173,216,230);
		case deepWater:
			return Color.BLUE;
		case sand:
			return Color.yellow;
		default:
			return Color.gray;
		}
	}
	//This will increase or decrease depending on the rule passed
	//Time: O(n) with const -> O(2n)
	static void checkRule(Map<types, Float> possible,types t2, Ruleable rule) {
		float increase = 0;
		float value;
		float newValue;
		float zeroIncrease = 0;
		Map<types, Float> increasing_only = new HashMap<>();
		for (types t: possible.keySet()) {
			value = rule.compare(t,t2);
			if (value < 0) {
				//increase possible and decrease key in map
				float diff = value *possible.get(t) * -1;
				increase += diff;
				
				possible.put(t, possible.get(t) - diff);
			}
			else {
				increasing_only.put(t, possible.get(t));
				if (possible.get(t) == 0) {
					zeroIncrease += value;
				}
			}
			
		}
		
		//Recomputing is const time so I figure its faster than accessing memory.  Essentially a lookup table anyway
		for (types t: increasing_only.keySet()) {
			value = rule.compare(t,t2);
			value = value + value*zeroIncrease;
			if(possible.get(t)==0) {
				value = 0;
			}
			//Increases the value of the possible by the proportion of value and total amount of prob. 
			
			possible.put(t,possible.get(t) +value*increase);
		}
		
	}
	
	public String toString() {
		String s = "[" + xCord + ", " + yCord + "]  ";
		for (types t:possible.keySet()) {
			s+=t +": " +possible.get(t) + "  ";
		}
		return s +"\n";
	}
}
