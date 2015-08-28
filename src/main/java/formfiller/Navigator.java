package formfiller;

import formfiller.entities.FormComponent;

public class Navigator {
	public enum Direction { BACKWARD, FORWARD }
	
	int currentIndex = -1;
	boolean isFinished = false;
	FormComponent current;
	
	public void move(Direction direction){
		if (!moveChangesPosition(direction)) return;
		
		if (direction == Direction.FORWARD)
			++currentIndex;
		else
			--currentIndex;
		setCurrent();		
	}

	private boolean moveChangesPosition(Direction direction) {
		if (direction == Direction.BACKWARD && currentIndex <= 0)
			return false;
		else if (direction == Direction.FORWARD && isFinished)
			return false;
		else
			return true;
	}

	private void setCurrent() {
		current = getCurrent();
	}

	public FormComponent getCurrent() {
		return ApplicationContext.formComponentGateway.
				findByIndex(currentIndex);
	}
}
