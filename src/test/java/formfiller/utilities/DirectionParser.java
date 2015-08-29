package formfiller.utilities;

import formfiller.gateways.Transporter.Direction;

public class DirectionParser {
	
	public static Direction parseDirection(String input){
		if (input.equalsIgnoreCase("forward")) return Direction.FORWARD;
		else if (input.equalsIgnoreCase("in place")) return Direction.IN_PLACE;
		else return Direction.BACKWARD;
	}
}
