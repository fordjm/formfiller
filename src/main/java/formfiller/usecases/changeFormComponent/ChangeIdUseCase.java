package formfiller.usecases.changeFormComponent;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.ChangeIdRequest;
import formfiller.request.models.Request;
import formfiller.utilities.GeneralUtilities;

public class ChangeIdUseCase extends ChangeFormComponent {
	private ChangeIdRequest castRequest;
	private String newId = "";
	
	protected void castRequest(Request request) {
		castRequest = (ChangeIdRequest) request;
	}

	protected boolean isRequestMalformed() {
		return GeneralUtilities.isStringNullOrEmpty(castRequest.oldId) || 
				GeneralUtilities.isStringNullOrEmpty(castRequest.newId);
	}

	protected void assignInstanceVariables() {
		id = castRequest.oldId;
		newId = castRequest.newId;
	}

	//	TODO:	Fix fragile duplicated value?
	protected void change(FormComponent component) {
		FormFillerContext.formComponentGateway.remove(id);
		component.question.id = newId;
		component.id = newId;
		FormFillerContext.formComponentGateway.save(component);
	}

	protected String makeSuccessfulMessage() {
		String result = "You successfully changed the id from " + 
				GeneralUtilities.makeQuotedString(id) + " to " + 
				GeneralUtilities.makeQuotedString(newId);
		return result;
	}

	//	TODO:	Implement
	public void undo() { }
}