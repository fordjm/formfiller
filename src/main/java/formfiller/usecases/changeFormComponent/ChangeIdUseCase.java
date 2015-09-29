package formfiller.usecases.changeFormComponent;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.ChangeIdRequest;
import formfiller.request.models.Request;
import formfiller.utilities.StringUtilities;

public class ChangeIdUseCase extends ChangeFormComponentUseCase {
	private ChangeIdRequest castRequest;
	private String oldId = "";
	private String newId = "";
	
	protected void castRequest(Request request) {
		castRequest = (ChangeIdRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.oldId) || 
				StringUtilities.isStringNullOrEmpty(castRequest.newId);
	}

	protected void assignInstanceVariables() {
		id = castRequest.oldId;
		newId = castRequest.newId;
	}

	protected void createUndoInfo(FormComponent found) {
		oldId = found.id;
	}

	//	TODO:	Fix fragile duplicated value?
	protected void change(FormComponent component) {
		swapNames(component.id, newId);
	}

	private void swapNames(String prevId, String nextId) {
		FormComponent changed = FormFillerContext.formComponentGateway.remove(prevId);
		changed.question.id = nextId;
		changed.id = nextId;
		FormFillerContext.formComponentGateway.save(changed);
	}

	protected String makeSuccessfulMessage() {
		String result = "You successfully changed the id from " + 
				StringUtilities.makeQuotedString(id) + " to " + 
				StringUtilities.makeQuotedString(newId);
		return result;
	}

	public void undo() {
		ensureUseCaseStateIsUndoable();
		swapNames(newId, oldId);
	}
	
}
