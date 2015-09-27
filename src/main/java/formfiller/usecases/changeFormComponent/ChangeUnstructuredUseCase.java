package formfiller.usecases.changeFormComponent;

import formfiller.entities.answerFormat.Unstructured;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.ChangeUnstructuredRequest;
import formfiller.request.models.Request;
import formfiller.utilities.StringUtilities;

public class ChangeUnstructuredUseCase extends ChangeFormComponent {
	private ChangeUnstructuredRequest castRequest;

	protected void castRequest(Request request) {
		castRequest = (ChangeUnstructuredRequest) request;
	}
	
	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId);
	}
	
	protected void assignInstanceVariables() {
		id = castRequest.componentId;
	}

	protected void change(FormComponent component) {
		component.format = new Unstructured();
	}
	
	protected String makeSuccessfulMessage() {
		return "You successfully changed the format to unstructured";
	}
	
	//	TODO:	Implement
	public void undo() { }
}
