package formfiller.usecases.askQuestion;

import formfiller.Context;
import formfiller.EventSinks;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Outcome;
import formfiller.enums.QuestionAsked;
import formfiller.gateways.impl.InMemoryTransporter;
import formfiller.request.models.AskQuestionRequest;
import formfiller.request.models.Request;
import formfiller.response.models.NotificationResponseModel;
import formfiller.usecases.undoable.UndoableUseCase;

public class AskQuestionUseCase implements UndoableUseCase {
	private QuestionAsked whichQuestion = QuestionAsked.CURRENT;
	private Outcome outcome = Outcome.NEUTRAL;

	public void execute(Request request) {		
		if (request == null) return;

		AskQuestionRequest askQuestionRequest = (AskQuestionRequest) request;
		whichQuestion = askQuestionRequest.which;

		if (!AskQuestionValidator.isValidQuestion(whichQuestion)) {
			temporarilyPopulateOutcomePresenterForFitNesseFixture();
			throw new RequiredAnswer(getAnswerRequiredMessage());
		}
		
		setOutcome(Outcome.POSITIVE);
		executeAskQuestion(whichQuestion);		

		presentAskQuestionResponse();
		Context.executedUseCases.add(this);
	}

	private void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	private void executeAskQuestion(QuestionAsked which) {
		new InMemoryTransporter().moveToElement(which);
	}

	private void presentAskQuestionResponse() {
		AskQuestionResponseModel responseModel = makeResponseModel();
		AskQuestionPresenter presenter = new AskQuestionPresenter();
		presenter.present(responseModel);
		EventSinks.receive(presenter.getViewModel());
	}
	
	private AskQuestionResponseModel makeResponseModel() {
		AskQuestionResponseModel result = new AskQuestionResponseModel();
		FormComponent current = getCurrentFormComponent();
		result.id = current.id;
		result.format = current.format.getName();
		result.message = current.question.getContent();
		result.answerContent = current.answer.getContent();	// TODO:	Fix this for multiple answers.
		return result;
	}

	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires an answer.";
	}	

	private FormComponent getCurrentFormComponent() {
		return Context.formComponentState.getCurrent();
	}

	public void undo() {
		if (!succeeded()) return;
		QuestionAsked which = getUndoWhichQuestion();
		executeAskQuestion(which);
	}
	
	private boolean succeeded(){
		return outcome == Outcome.POSITIVE;
	}

	private QuestionAsked getUndoWhichQuestion() {
		if (whichQuestion == QuestionAsked.CURRENT)
			return whichQuestion;
		else if (whichQuestion == QuestionAsked.NEXT)
			return QuestionAsked.PREVIOUS;
		else
			return QuestionAsked.NEXT;
	}

	private void temporarilyPopulateOutcomePresenterForFitNesseFixture() {
		if (succeeded()) return;
		
		populateOutcomePresenterForFitNesseFixture();
	}
	
	private void populateOutcomePresenterForFitNesseFixture() {
		NotificationResponseModel response = new NotificationResponseModel();
		response.message = getAnswerRequiredMessage();
		Context.outcomePresenter.present(response);
	}

	public class RequiredAnswer extends RuntimeException {
		public RequiredAnswer(String message) {
			super(message);
		}
	}
}
