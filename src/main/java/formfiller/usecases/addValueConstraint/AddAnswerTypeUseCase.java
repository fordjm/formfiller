package formfiller.usecases.addValueConstraint;

import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddAnswerTypeRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddAnswerTypeUseCase extends UndoableUseCaseExecution{
	private AddAnswerTypeRequest castRequest;
	private FormComponent found;
	private AnswerType constraint;

	protected void castRequest(Request request) {
		castRequest = (AddAnswerTypeRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId);
	}

	protected void execute() {
		found = FormComponentUtilities.find(castRequest.componentId);
		constraint = new AnswerType(castRequest.type);
		found.validator.addConstraint(constraint);
	}

	protected String makeSuccessfulMessage() {
		return "You successfully added the answer type " + castRequest.type;
	}

	//	TODO:	Implement AnswerValidator.removeConstraint(Constrainable)
	public void undo() {
		ensureUseCaseStateIsUndoable();
		found = FormComponentUtilities.find(castRequest.componentId);
		found.validator.constraints.remove(constraint);
	}

}
