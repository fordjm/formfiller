package formfiller.usecases.addFormComponent;

import java.util.ArrayList;
import java.util.Collection;

import formfiller.Context;
import formfiller.entities.Question;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.usecases.addAnswer.AnswerValidator;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.StringUtilities;

public class AddFormComponentUseCase extends UndoableUseCaseExecution {
	private AddFormComponentRequest castRequest;
	
	protected void castRequest(Request request) {
		castRequest = (AddFormComponentRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId);
	}

	protected void execute() {
		FormComponent newComponent = makeNewFormComponent();
		newComponent.format = castRequest.format;
		newComponent.validator = new AnswerValidator(makeAnswerConstraints());
		Context.formComponentGateway.save(newComponent);
	}

	private FormComponent makeNewFormComponent() {
		FormComponent result = new FormComponent();
		result.id = castRequest.componentId;
		result.question = makeNewQuestion();
		return result;
	}

	private Question makeNewQuestion() {
		Question result = new Question();
		result.id = castRequest.componentId;
		result.content = castRequest.questionContent;
		return result;
	}

	private Collection<Constrainable> makeAnswerConstraints() {
		Collection<Constrainable> result = new ArrayList<Constrainable>();
		result.add(new AnswerType(castRequest.answerType));
		return result;
	}

	protected String makeSuccessfulMessage() {
		String result = "You successfully added the new form component, \"" +
				castRequest.componentId + ".\"";
		return result;
	}

	public void undo() { 
		ensureUseCaseStateIsUndoable();
		Context.formComponentGateway.remove(castRequest.componentId);
	}
	
}
