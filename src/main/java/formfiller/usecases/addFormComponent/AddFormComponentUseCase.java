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
import formfiller.response.models.PresentableResponse;
import formfiller.usecases.addAnswer.AnswerValidator;
import formfiller.usecases.undoable.UndoableUseCase;

public abstract class AddFormComponentUseCase implements UndoableUseCase {
	private Outcome outcome = Outcome.POSITIVE;
	// TODO:	Handle malformed requests.
	//			Add questionId, questionContent, format, and validator here.
	//			Use IVs in class methods.
	
	protected abstract AnswerFormat makeAnswerFormat(int minAnswers, int maxAnswers);
	
	public void execute(Request request) {
		if (request == null) return;
		
		AddFormComponentRequest castRequest = 
				(AddFormComponentRequest) request;
		if (isMalformedRequest(castRequest)) return;
		
		FormComponent newComponent = makeNewFormComponent(castRequest.questionId, castRequest.questionContent);
		newComponent.format = makeAnswerFormat(0, 1);
		newComponent.validator = new AnswerValidator(makeAnswerConstraints(castRequest));
		
		FormFillerContext.formComponentGateway.save(newComponent);
		
		PresentableResponse response = makeResponse(castRequest.questionId);		
		presentResponse(response);
		addToExecutedUseCasesIfSuccessful();
	}

	//	TODO:	Expand coverage, return error message.
	private boolean isMalformedRequest(AddFormComponentRequest castRequest) {
		return castRequest.questionId == null || castRequest.questionId == "";
	}

	private void addToExecutedUseCasesIfSuccessful() {
		if (outcome == Outcome.POSITIVE)
			FormFillerContext.executedUseCases.add(this);
	}

	private FormComponent makeNewFormComponent(String questionId, String questionContent) {
		FormComponent result = new FormComponent();
		result.id = questionId;
		result.question = makeNewQuestion(questionId, questionContent);
		return result;
	}

	private Collection<Constrainable> makeAnswerConstraints(AddFormComponentRequest castRequest) {
		Collection<Constrainable> result = new ArrayList<Constrainable>();
		result.add(new AnswerType(castRequest.answerType));
		return result;
	}

	private Question makeNewQuestion(String questionId, String questionContent) {
		Question result = new Question();
		result.id = questionId;
		result.content = questionContent;
		return result;
	}
	
	private PresentableResponse makeResponse(String questionId) {
		PresentableResponse result = new PresentableResponse();
		result.message = makeMessage(questionId);
		result.outcome = Outcome.NEGATIVE;
		return result;
	}

	private String makeMessage(String questionId) {
		String result = "You successfully added the new form component, \"" +
				questionId + ".\"";
		return result;
	}
	
	private void presentResponse(PresentableResponse presentableResponse) {
		FormFillerContext.errorPresenter.present(presentableResponse);
	}

	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
