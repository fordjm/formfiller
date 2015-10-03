package formfiller.usecases.addFormatConstraint;

import formfiller.entities.answerFormat.OptionVariable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddOptionRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddOptionUseCase extends UndoableUseCaseExecution {
	AddOptionRequest castRequest;

	protected void castRequest(Request request) {
		castRequest = (AddOptionRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId) || 
				StringUtilities.isStringNullOrEmpty(castRequest.option);
	}

	protected void execute() {
		FormComponent component = FormComponentUtilities.find(castRequest.componentId);
		OptionVariable format = (OptionVariable) component.format;
		format.options.add(castRequest.option);
	}

	protected String makeSuccessfulMessage() {
		String quotedOption = StringUtilities.makeQuotedString(castRequest.option);
		return "You successfully added the option, " + quotedOption;
	}

	public void undo() {
		ensureUseCaseStateIsUndoable();
		FormComponent component = FormComponentUtilities.find(castRequest.componentId);
		OptionVariable format = (OptionVariable) component.format;
		format.options.remove(castRequest.option);
	}

}
