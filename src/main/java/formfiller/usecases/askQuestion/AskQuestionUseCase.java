package formfiller.usecases.askQuestion;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.Presenter;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Outcome;
import formfiller.enums.WhichQuestion;
import formfiller.gateways.InMemoryTransporter;
import formfiller.request.models.*;
import formfiller.response.models.*;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.utilities.PresenterSelector;

public class AskQuestionUseCase implements UndoableUseCase {
	private WhichQuestion whichQuestion = WhichQuestion.CURRENT;
	private Outcome outcome = Outcome.NEUTRAL;

	public void execute(Request request) {		
		if (request == null) return;

		AskQuestionRequest askQuestionRequest = (AskQuestionRequest) request;
		whichQuestion = askQuestionRequest.which;

		if (AskQuestionValidator.isValidQuestion(whichQuestion)) {
			setOutcome(Outcome.POSITIVE);
			executeAskQuestion(whichQuestion);
		} else 
			setOutcome(Outcome.NEGATIVE);

		PresentableResponse response = makeResponse();		
		presentResponse(response);
		FormFillerContext.executedUseCases.add(this);
	}

	private void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	private PresentableResponse makeResponse() {
		return (outcome == Outcome.POSITIVE) 
				? makePresentableFormComponent() : makePresentableResponse();
	}

	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires an answer.";
	}

	private void executeAskQuestion(WhichQuestion which) {
		new InMemoryTransporter().moveToElement(which);
	}

	private void presentResponse(PresentableResponse presentableResponse) {
		Presenter presenter = 
				PresenterSelector.selectPresenter(presentableResponse.outcome);
		presenter.present(presentableResponse);
	}	

	//	TODO:	AskQuestion should know nothing about PresentableResponses.
	//			Make a factory.	
	private PresentableResponse makePresentableResponse() {
		PresentableResponse result = new PresentableResponse();
		result.message = getAnswerRequiredMessage();
		result.outcome = Outcome.NEGATIVE;
		return result;
	}

	private PresentableResponse makePresentableFormComponent() {		
		FormComponent current = getCurrentFormComponent();
		PresentableFormComponent result = new PresentableFormComponent();
		result.question = makePresentableQuestion(current.question);
		result.answer = makePresentableAnswer(current.answer);
		result.outcome = Outcome.POSITIVE;
		result.message = result.question.message;	//	TODO:  Different FC message?
		return result;
	}

	private FormComponent getCurrentFormComponent() {
		return FormFillerContext.formComponentState.getCurrent();
	}

	private PresentableQuestion makePresentableQuestion(Question requestedQuestion) {
		PresentableQuestion result = new PresentableQuestion();
		result.id = requestedQuestion.id;
		result.message = requestedQuestion.content;
		return result;
	}

	private PresentableAnswer makePresentableAnswer(Answer requestedAnswer) {
		PresentableAnswer result = new PresentableAnswer();
		result.id = requestedAnswer.id;
		result.message = requestedAnswer.content.toString();
		return result;
	}

	public void undo() {
		if (!succeeded()) return;
		WhichQuestion which = getUndoWhichQuestion();
		executeAskQuestion(which);
	}
	
	private boolean succeeded(){
		return outcome == Outcome.POSITIVE;
	}

	private WhichQuestion getUndoWhichQuestion() {
		if (whichQuestion == WhichQuestion.CURRENT)
			return whichQuestion;
		else if (whichQuestion == WhichQuestion.NEXT)
			return WhichQuestion.PREV;
		else
			return WhichQuestion.NEXT;
	}
}
