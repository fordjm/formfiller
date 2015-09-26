package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.response.models.PresentableResponse;

public class DeleteFormComponent {
	private StringEventManager fixtureEventHandler;

	public DeleteFormComponent() {
		fixtureEventHandler = new StringEventManager();
	}
	
	public void whenTheUserDeletesTheFormComponent(String componentId){
		fixtureEventHandler.updateHandler("DelFC " + componentId);
	}

	//	TODO:	Fix duplication in AddFormComponent fixture.
	//			Add fixture utilities class?
	public String messagePresented() {
		PresentableResponse response = 
				FormFillerContext.outcomePresenter.getPresentableResponse();
		return response.message;
	}
	
	public boolean didNotFindComponent(String componentId) {
		FormComponent foundComponent = FormFillerContext.formComponentGateway.find(componentId);
		return foundComponent.equals(NullFormComponents.NULL);
	}
}
