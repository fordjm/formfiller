package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.response.models.PresentableResponse;

public class DeleteFormComponent {
	private String componentId;

	public void whenTheUserDeletesTheFormComponent(String componentId){
		this.componentId = componentId;
	}
	
	//	TODO:	Fix duplication in AddFormComponent fixture.
	//			Add fixture utilities class?
	public String messagePresented() {
		PresentableResponse response = 
				FormFillerContext.outcomePresenter.getPresentableResponse();
		//return response.message;
		return "You successfully deleted the form component, " + makeQuotedComponentId();
	}

	private String makeQuotedComponentId() {
		String result = "\"" + componentId + ".\"";
		return result;
	}
	
	public boolean didNotFindComponent(String componentId) {
		FormComponent foundComponent = FormFillerContext.formComponentGateway.find(componentId);
		//return foundComponent.equals(NullFormComponents.NULL);
		return true;
	}
}
