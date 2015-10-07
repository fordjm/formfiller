package formfiller.usecases.changeFormComponent;

import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;
import formfiller.request.models.Request;
import formfiller.request.models.RequestWithComponentId;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public abstract class ChangeFormatUseCase extends ChangeFormComponentUseCase {
	protected Format oldFormat;
	protected Format newFormat;
	protected RequestWithComponentId castRequest;
	
	protected void castRequest(Request request) {
		castRequest = (RequestWithComponentId) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId);
	}

	protected void assignInstanceVariables() {
		id = castRequest.componentId;
	}
	
	protected void createUndoInfo(FormComponent component) {
		oldFormat = component.format;
	}
	
	protected void change(FormComponent component) {
		component.format = newFormat;
	}

	protected String makeSuccessfulMessage() {
		return "You successfully changed the format to " + newFormat.getName();
	}

	public void undo() {
		ensureUseCaseStateIsUndoable();
		FormComponent toRevert = FormComponentUtilities.find(id);
		toRevert.format = oldFormat;
	}
	
}