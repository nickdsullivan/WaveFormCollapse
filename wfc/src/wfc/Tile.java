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
	public  Tile() {
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
	public void update(types t, int dist) {
		checkRule( (t) ->{
			
		})
		
	}
	//Here I collapse the possible states.
	
	//This is doing weighted picking
	//choose the smallest prob and add it to the floor and put it back in
	//Now choose a random number and find the smallest float that is bigger than it and thats the tile you select.
	//Works because floats always add to 1
	public void collapse() {
		
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
		
		System.out.println(rand);
		for (types t: possible.keySet()) {
			
			if (rand < possible.get(t) && possible.get(t) < minURand) {
				currMaxURand =t;
				minURand = possible.get(t);
				
			}
		}
		this.current = currMaxURand;
		//update the possibles to 0 now
		System.out.println(this.current);
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
		case deepwater:
			return Color.BLUE;
		case sand:
			return Color.yellow;
		default:
			return Color.gray;
		}
	}
	//This will increase or decrease depending on the value
	static void checkRule(types t1, types t2, Ruleable rule) {
		rule.compare(t2);
	}
}
