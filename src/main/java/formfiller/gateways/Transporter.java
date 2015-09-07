package formfiller.gateways;

import formfiller.enums.Direction;

public interface Transporter {	
	void moveInDirection(Direction direction);
}