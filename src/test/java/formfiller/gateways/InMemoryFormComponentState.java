package formfiller.gateways;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.enums.QuestionAsked;

public class InMemoryFormComponentState implements FormComponentState {		
	public int currentIndex = 0;
	
	public FormComponent getCurrent(){
		InMemoryFormComponentGateway castGateway = 
				(InMemoryFormComponentGateway) Context.formComponentGateway;
		return castGateway.findByIndex(currentIndex);
	}
	
	public boolean isAtEnd(){
		return getCurrent() == NullFormComponents.END;
	}

	//	TODO:	Name this more accurately
	public boolean isAtStart() {
		return currentIndex < 0;
	}
	
	public void update(QuestionAsked direction){
		if (direction == QuestionAsked.NEXT)
			++currentIndex;
		else
			--currentIndex;
	}
}