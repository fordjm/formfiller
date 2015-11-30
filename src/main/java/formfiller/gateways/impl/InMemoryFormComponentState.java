package formfiller.gateways.impl;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.enums.QuestionAsked;
import formfiller.gateways.FormComponentState;

public class InMemoryFormComponentState implements FormComponentState {		
	public int currentIndex = 0;	//	This is the problem.
	//	public String lastId?  (what if lastId's component is removed?)
	
	public FormComponent getCurrent(){
		//	TODO:	Get ID from FCPresenter and retrieve from Gateway?  (No, no reliance on Presenter access.)
		
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
	
	//	NOTE:	Pre- and post-incrementer are not thread-safe.
	public void update(QuestionAsked direction){
		if (direction == QuestionAsked.NEXT)
			++currentIndex;
		else
			--currentIndex;
	}
}