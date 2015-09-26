package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;

public class DeleteFormComponent {
	private StringEventManager fixtureEventHandler;

	public DeleteFormComponent() {
		fixtureEventHandler = new StringEventManager();
	}
	
	public void whenTheUserDeletesTheFormComponent(String componentId){
		fixtureEventHandler.updateHandler("DelFC " + componentId);
	}
	
	public boolean didNotFindComponent(String componentId) {
		FormComponent foundComponent = FormFillerContext.formComponentGateway.find(componentId);
		return foundComponent.equals(NullFormComponents.NULL);
	}
}
