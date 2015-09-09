package formfiller.gateways;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.enums.Direction;

public class InMemoryFormComponentState implements FormComponentState {		
	public int currentIndex = 0;
	
	public FormComponent getCurrent(){
		InMemoryFormComponentGateway castGateway = 
				(InMemoryFormComponentGateway) FormFillerContext.formComponentGateway;
		return castGateway.findByIndex(currentIndex);
	}
	
	public boolean isAtEnd(){
		return getCurrent() == NullFormComponents.END;
	}

	//	TODO:	Name this more accurately
	public boolean isAtStart() {
		return currentIndex < 0;
	}
	
	public void update(Direction direction){
		if (direction == Direction.FORWARD)
			++currentIndex;
		else
			--currentIndex;
	}
}