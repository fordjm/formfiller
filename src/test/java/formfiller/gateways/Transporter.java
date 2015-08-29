package formfiller.gateways;

import formfiller.ApplicationContext;
import formfiller.entities.FormComponent;

public class Transporter {
	public enum Direction { BACKWARD, FORWARD, IN_PLACE }
	
	int currentIndex = 0;
	boolean isFinished = false;
	
	public void move(Direction direction){
		if (!moveChangesPosition(direction)) return;
		
		if (direction == Direction.FORWARD)
			++currentIndex;
		else
			--currentIndex;
	}

	private boolean moveChangesPosition(Direction direction) {
		if (direction == Direction.IN_PLACE) 
			return false;
		else if (direction == Direction.BACKWARD && currentIndex < 0)
			return false;
		else if (direction == Direction.FORWARD && isFinished)
			return false;
		else
			return true;
	}

	public FormComponent getCurrent() {
		return ApplicationContext.formComponentGateway.
				findByIndex(currentIndex);
	}
}
