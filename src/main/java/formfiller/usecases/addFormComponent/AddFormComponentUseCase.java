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
	protected AddFormComponentRequest castRequest;
	private String questionId;
	private String questionContent;
	private Outcome outcome = Outcome.POSITIVE;
	private String message = "";
	
	// TODO:	Handle malformed requests and set outcome.
	//			Add questionId, questionContent, format, and validator here.
	//			Use IVs in class methods.
	
	//	TODO:	Remove mandatory params.
	protected abstract AnswerFormat makeAnswerFormat(int minAnswers, int maxAnswers);
	
	public void execute(Request request) {
		castRequest(request);
		assignInstanceVariables();
		checkForMalformedRequest();
		execute();		
		PresentableResponse response = makeResponse();		
		presentResponse(response);
	}

	protected void castRequest(Request request) {
		castRequest = (AddFormComponentRequest) request;
	}

	protected void checkForMalformedRequest() {
		if (castRequest.questionId == null || castRequest.questionId == "")
			throw new MalformedRequest("The question I.D. was illegal.");
	}

	protected void assignInstanceVariables() {
		questionId = castRequest.questionId;
		questionContent = castRequest.questionContent;
	}

	protected void execute() {
		FormComponent newComponent = makeNewFormComponent();
		newComponent.format = makeAnswerFormat(0, 1);
		newComponent.validator = new AnswerValidator(
				makeAnswerConstraints(castRequest));
		FormFillerContext.formComponentGateway.save(newComponent);
		handleSuccessfulUseCase();
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

	private void handleSuccessfulUseCase() {
		outcome = Outcome.POSITIVE;
		this.message = makeSuccessfulMessage();
		addToExecutedUseCases();
	}

	protected void addToExecutedUseCases() {
		FormFillerContext.executedUseCases.add(this);
	}

	private Collection<Constrainable> makeAnswerConstraints(AddFormComponentRequest castRequest) {
		Collection<Constrainable> result = new ArrayList<Constrainable>();
		result.add(new AnswerType(castRequest.answerType));
		return result;
	}
	
	//	TODO:	Extract to abstract superclass
	protected PresentableResponse makeResponse() {
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
	
	protected void presentResponse(PresentableResponse presentableResponse) {
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
