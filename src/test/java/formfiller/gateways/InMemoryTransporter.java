package formfiller.gateways;

import formfiller.ApplicationContext;
import formfiller.entities.FormComponent;

public class InMemoryTransporter implements Transporter {
	public enum Direction { BACKWARD, FORWARD, NONE }
	
	public final NavigationValidator navigationValidator = 
			new NavigationValidator();
	
	int currentIndex = 0;
	boolean isFinished = false;

	public FormComponent getCurrent() {
		return getInMemoryFormComponentGateway().findByIndex(currentIndex);
	}
	
	private InMemoryFormComponentGateway getInMemoryFormComponentGateway(){
		InMemoryFormComponentGateway result = (InMemoryFormComponentGateway)
				ApplicationContext.formComponentGateway;		
		return result;
	}
	
	public NavigationValidator getNavigationValidator(){
		return navigationValidator;
	}
	
	public void move(Direction direction){
		if (!moveChangesPosition(direction)) 
			return;
		
		if (direction == Direction.FORWARD)
			++currentIndex;
		else
			--currentIndex;
	}

	private boolean moveChangesPosition(Direction direction) {
		if (direction == Direction.NONE) 
			return false;
		else if (direction == Direction.BACKWARD && currentIndex < 0)
			return false;
		else if (direction == Direction.FORWARD && isFinished)
			return false;
		else
			return true;
	}
}
