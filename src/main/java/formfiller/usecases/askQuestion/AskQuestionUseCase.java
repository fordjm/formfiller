package formfiller.usecases.askQuestion;

import java.util.ArrayList;
import java.util.Collection;

import formfiller.Context;
import formfiller.appBoundaries.Presenter;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.QuestionImpl;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Outcome;
import formfiller.enums.QuestionAsked;
import formfiller.gateways.impl.InMemoryTransporter;
import formfiller.request.models.*;
import formfiller.response.models.*;
import formfiller.usecases.undoable.UndoableUseCase;

public class AskQuestionUseCase implements UndoableUseCase {
	private QuestionAsked whichQuestion = QuestionAsked.CURRENT;
	private Outcome outcome = Outcome.NEUTRAL;

	public void execute(Request request) {		
		if (request == null) return;

		clearAllPresenters();
		AskQuestionRequest askQuestionRequest = (AskQuestionRequest) request;
		whichQuestion = askQuestionRequest.which;

		if (AskQuestionValidator.isValidQuestion(whichQuestion)) {
			setOutcome(Outcome.POSITIVE);
			executeAskQuestion(whichQuestion);
		} else {
			// TODO:  Present failure in same presenter as success.  Maybe.
			setOutcome(Outcome.NEGATIVE);
		}			

		PresentableResponse response = makeResponse();		
		presentResponse(response);
		Context.executedUseCases.add(this);
	}

	private void clearAllPresenters() {
		for (Presenter presenter : getPresenters())
			presenter.clearPresentableResponse();
	}

	//	TODO:	Fix duplication in FormComponentPresentation
	private Collection<Presenter> getPresenters() {
		Collection<Presenter> result = new ArrayList<Presenter>();
		result.add(Context.questionPresenter);
		result.add(Context.answerPresenter);
		result.add(Context.outcomePresenter);
		return result;
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

	private void executeAskQuestion(QuestionAsked which) {
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
		return Context.formComponentState.getCurrent();
	}

	private PresentableQuestion makePresentableQuestion(Question requestedQuestion) {
		PresentableQuestion result = new PresentableQuestion();
		result.id = requestedQuestion.getId();
		result.message = requestedQuestion.getContent();
		return result;
	}

	private PresentableAnswer makePresentableAnswer(Answer requestedAnswer) {
		PresentableAnswer result = new PresentableAnswer();
		result.id = requestedAnswer.getId();
		result.message = requestedAnswer.getContent().toString();
		return result;
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
}
