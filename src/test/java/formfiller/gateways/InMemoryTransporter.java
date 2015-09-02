package formfiller.gateways;

import formfiller.ApplicationContext;
import formfiller.entities.FormComponent;

public class InMemoryTransporter implements Transporter {
	public enum Direction { BACKWARD, FORWARD, NONE }	
	public final NavigationValidator navigationValidator = 
			new NavigationValidator();
	private final FormState currentState = new FormState();

	public FormComponent getCurrent() {
		return getInMemoryFormComponentGateway().findByIndex(currentState.currentIndex);
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
			++currentState.currentIndex;
		else
			--currentState.currentIndex;
	}

	private boolean moveChangesPosition(Direction direction) {
		if (direction == Direction.NONE) 
			return false;
		else if (direction == Direction.BACKWARD && currentState.currentIndex < 0)
			return false;
		else if (direction == Direction.FORWARD && currentState.isFinished)
			return false;
		else
			return true;
	}
	
	private class FormState {		
		int currentIndex = 0;
		boolean isFinished = false;		
	}
}
