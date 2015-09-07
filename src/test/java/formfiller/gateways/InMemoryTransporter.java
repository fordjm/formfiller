package formfiller.gateways;

import formfiller.FormFillerContext;
import formfiller.enums.Direction;

public class InMemoryTransporter implements Transporter {
	
	public void moveInDirection(Direction direction){
		if (!moveChangesPosition(direction)) 
			return;
		
		getCurrentState().update(direction);
	}
	
	private static FormComponentState getCurrentState(){
		return FormFillerContext.formComponentState;
	}

	private static boolean moveChangesPosition(Direction direction) {
		if (direction == Direction.NONE) 
			return false;
		else if (direction == Direction.BACKWARD && 
				getCurrentState().isAtStart())
			return false;
		else if (direction == Direction.FORWARD && 
				getCurrentState().isAtEnd())
			return false;
		else
			return true;
	}
}
