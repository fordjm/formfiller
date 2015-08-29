package formfiller.gateways;

import formfiller.ApplicationContext;
import formfiller.entities.FormComponent;

public class Transporter {
	public enum Direction { BACKWARD, FORWARD, NONE }
	public final Navigator navigator = new Navigator();
	
	int currentIndex = 0;
	boolean isFinished = false;

	public FormComponent getCurrent() {
		return ApplicationContext.formComponentGateway.
				findByIndex(currentIndex);
	}
	
	public void move(Direction direction){
		if (!navigator.moveChangesPosition(direction, currentIndex, isFinished)) 
			return;
		
		if (direction == Direction.FORWARD)
			++currentIndex;
		else
			--currentIndex;
	}
}
