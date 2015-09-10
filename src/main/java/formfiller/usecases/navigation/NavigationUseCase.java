package formfiller.usecases.navigation;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.Presenter;
import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Outcome;
import formfiller.enums.Direction;
import formfiller.gateways.InMemoryTransporter;
import formfiller.request.models.*;
import formfiller.response.models.*;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.utilities.PresenterSelector;

//	TODO:	MoveInDirectionUseCase?  MoveUseCase?
public class NavigationUseCase implements UndoableUseCase {
	private Direction direction = Direction.NONE;
	private Outcome outcome = Outcome.NEUTRAL;

	public void execute(Request request) {		
		if (request == null) return;

		NavigationRequest navigationRequest = (NavigationRequest) request;
		direction = navigationRequest.direction;

		if (NavigationValidator.isValidDirectionalMove(direction)) {
			setOutcome(Outcome.POSITIVE);
			executeMoveInDirection(direction);
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

	private void executeMoveInDirection(Direction direction) {
		new InMemoryTransporter().moveInDirection(direction);
	}

	private void presentResponse(PresentableResponse presentableResponse) {
		Presenter presenter = 
				PresenterSelector.selectPresenter(presentableResponse.outcome);
		presenter.present(presentableResponse);
	}	

	//	TODO:	Navigation should know nothing about PresentableResponses.
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
		Direction direction = getUndoDirection();
		executeMoveInDirection(direction);
	}
	
	private boolean succeeded(){
		return outcome == Outcome.POSITIVE;
	}

	private Direction getUndoDirection() {
		if (direction == Direction.NONE)
			return direction;
		else if (direction == Direction.FORWARD)
			return Direction.BACKWARD;
		else
			return Direction.FORWARD;
	}
}
