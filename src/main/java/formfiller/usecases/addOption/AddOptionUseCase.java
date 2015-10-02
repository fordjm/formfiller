package formfiller.usecases.addOption;

import formfiller.entities.answerFormat.OptionVariable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddOptionRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddOptionUseCase extends UndoableUseCaseExecution {
	AddOptionRequest castRequest;

	public void undo() {
		ensureUseCaseStateIsUndoable();
		FormComponent component = FormComponentUtilities.find(castRequest.componentId);
		OptionVariable format = (OptionVariable) component.format;
		format.options.remove(castRequest.option);
	}

	protected void castRequest(Request request) {
		castRequest = (AddOptionRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId) || 
				StringUtilities.isStringNullOrEmpty(castRequest.option);
	}

	//	TODO:	Determine whether this method is redundant in all cases.
	@Override
	protected void assignInstanceVariables() {

	}

	protected void execute() {
		FormComponent component = FormComponentUtilities.find(castRequest.componentId);
		OptionVariable format = (OptionVariable) component.format;
		format.options.add(castRequest.option);
	}

	protected String makeSuccessfulMessage() {
		return "You successfully added the option, \"" + castRequest.option + "\"";
	}

}
