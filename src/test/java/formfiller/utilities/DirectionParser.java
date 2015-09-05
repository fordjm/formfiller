package formfiller.utilities;

import formfiller.enums.Direction;

public class DirectionParser {
	
	public static Direction parseDirection(String input){
		if (input.equalsIgnoreCase("forward")) return Direction.FORWARD;
		else if (input.equalsIgnoreCase("none")) return Direction.NONE;
		else return Direction.BACKWARD;
	}
}
