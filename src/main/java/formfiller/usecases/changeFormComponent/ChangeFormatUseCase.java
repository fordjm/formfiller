package formfiller.usecases.changeFormComponent;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.ChangeFormatRequest;
import formfiller.request.models.Request;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public abstract class ChangeFormatUseCase extends ChangeFormComponentUseCase {
	protected AnswerFormat oldFormat;
	protected AnswerFormat newFormat;
	protected ChangeFormatRequest castRequest;
	
	protected void castRequest(Request request) {
		castRequest = (ChangeFormatRequest) request;
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
		FormComponent toRevert = FormComponentUtilities.findFormComponent(id);
		toRevert.format = oldFormat;
	}
	
}