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
	private String message = "";
	protected AddFormComponentRequest castRequest;
	
	// TODO:	Handle malformed requests and set outcome.
	//			Add questionId, questionContent, format, and validator here.
	//			Use IVs in class methods.
	
	//	TODO:	Remove mandatory params.
	protected abstract AnswerFormat makeAnswerFormat(int minAnswers, int maxAnswers);
	
	public void execute(Request request) {
		castRequest = (AddFormComponentRequest) request;
		checkForMalformedRequest();
		addComponent();		
		PresentableResponse response = makeResponse();		
		presentResponse(response);
	}

	private void checkForMalformedRequest() {
		if (castRequest.questionId == null || castRequest.questionId == "")
			throw new MalformedRequest("The question I.D. was illegal.");
	}

	private void addComponent() {
		FormComponent newComponent = makeNewFormComponent(castRequest.questionId, 
				castRequest.questionContent);
		newComponent.format = makeAnswerFormat(0, 1);
		newComponent.validator = new AnswerValidator(
				makeAnswerConstraints(castRequest));
		FormFillerContext.formComponentGateway.save(newComponent);
		handleSuccessfulUseCase();
	}

	private FormComponent makeNewFormComponent(String questionId, String questionContent) {
		FormComponent result = new FormComponent();
		result.id = questionId;
		result.question = makeNewQuestion(questionId, questionContent);
		return result;
	}

	private Question makeNewQuestion(String questionId, String questionContent) {
		Question result = new Question();
		result.id = questionId;
		result.content = questionContent;
		return result;
	}

	private void handleSuccessfulUseCase() {
		outcome = Outcome.POSITIVE;
		this.message = makeSuccessfulMessage();
		addToExecutedUseCases();
	}

	private void addToExecutedUseCases() {
		FormFillerContext.executedUseCases.add(this);
	}

	private Collection<Constrainable> makeAnswerConstraints(AddFormComponentRequest castRequest) {
		Collection<Constrainable> result = new ArrayList<Constrainable>();
		result.add(new AnswerType(castRequest.answerType));
		return result;
	}
	
	//	TODO:	Fix duplication in DeleteFormComponentUseCase
	private PresentableResponse makeResponse() {
		PresentableResponse result = new PresentableResponse();
		result.message = message;
		result.outcome = outcome;
		return result;
	}

	private String makeSuccessfulMessage() {
		String result = "You successfully added the new form component, \"" +
				castRequest.questionId + ".\"";
		return result;
	}
	
	private void presentResponse(PresentableResponse presentableResponse) {
		FormFillerContext.outcomePresenter.present(presentableResponse);
	}

	public void undo() {
		// TODO Auto-generated method stub
		
	}

	public class MalformedRequest extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MalformedRequest(String message){
			super(message);
		}
	}
}
