package formfiller.usecases.addValueConstraint;

import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddAnswerTypeRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddAnswerTypeUseCase extends UndoableUseCaseExecution{
	AddAnswerTypeRequest castRequest;

	protected void castRequest(Request request) {
		castRequest = (AddAnswerTypeRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId);
	}

	protected void execute() {
		FormComponent found = FormComponentUtilities.find(castRequest.componentId);
		AnswerType constraint = new AnswerType(castRequest.type);
		found.validator.addConstraint(constraint);
	}

	protected String makeSuccessfulMessage() {
		return "You successfully added the answer type " + castRequest.type;
	}

	//	TODO:	Implement (needs AnswerValidator.removeConstraint(Constrainable))
	public void undo() {
		ensureUseCaseStateIsUndoable();
		
	}

}
