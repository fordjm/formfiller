package formfiller.usecases.addFormComponent;

import java.util.ArrayList;
import java.util.Collection;

import formfiller.FormFillerContext;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Outcome;
import formfiller.request.models.AddFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.usecases.addAnswer.AnswerValidator;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.StringUtilities;

public abstract class AddFormComponentUseCase extends UndoableUseCaseExecution {
	private AddFormComponentRequest castRequest;
	private String questionId = "";
	private String questionContent = "";
	
	protected abstract AnswerFormat makeAnswerFormat();
	
	protected void castRequest(Request request) {
		castRequest = (AddFormComponentRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.questionId);
	}

	protected void assignInstanceVariables() {
		questionId = castRequest.questionId;
		questionContent = castRequest.questionContent;
	}

	protected void execute() {
		FormComponent newComponent = makeNewFormComponent();
		newComponent.format = makeAnswerFormat();
		newComponent.validator = new AnswerValidator(
				makeAnswerConstraints(castRequest));
		FormFillerContext.formComponentGateway.save(newComponent);
	}

	private FormComponent makeNewFormComponent() {
		FormComponent result = new FormComponent();
		result.id = questionId;
		result.question = makeNewQuestion();
		return result;
	}

	private Question makeNewQuestion() {
		Question result = new Question();
		result.id = questionId;
		result.content = questionContent;
		return result;
	}

	private Collection<Constrainable> makeAnswerConstraints(AddFormComponentRequest castRequest) {
		Collection<Constrainable> result = new ArrayList<Constrainable>();
		result.add(new AnswerType(castRequest.answerType));
		return result;
	}

	protected String makeSuccessfulMessage() {
		String result = "You successfully added the new form component, \"" +
				castRequest.questionId + ".\"";
		return result;
	}

	public void undo() { 
		ensureUseCaseIsUndoable();
		FormFillerContext.formComponentGateway.remove(questionId);
	}
	
}
