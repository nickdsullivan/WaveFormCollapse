package wfc;
import java.util.ArrayList;
public class Tile {
	types current;
	ArrayList<types> possible;
	//types [] possible;
	public  Tile() {
		current = types.unknown;
		possible =  new ArrayList<types>();
		for(types t: types.values()) {
			 checkRule(current, t, () ->{
				return(current != t);
			}); 
				
			
		}
	}
	
	static void checkRule(types t1, types t2, Ruleable rule) {
		rule.compare();
	}
}
