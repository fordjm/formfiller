package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.appBoundaries.Presenter;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.Answer;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.ActionOutcome;
import formfiller.enums.Direction;
import formfiller.gateways.InMemoryTransporter;
import formfiller.request.models.NavigationRequest;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableAnswer;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableQuestion;
import formfiller.response.models.PresentableResponse;

public class NavigationUseCase implements UseCase, Undoable {
	private Direction direction = Direction.NONE;
	private ActionOutcome outcome = ActionOutcome.NONE;

	public void execute(Request request) {		
		if (request == null) return;

		NavigationRequest navigationRequest = (NavigationRequest) request;
		direction = navigationRequest.direction;

		if (NavigationValidator.isValidMove(direction)) {
			setOutcome(ActionOutcome.SUCCEEDED);
			executeMove(direction);
		} else 
			setOutcome(ActionOutcome.FAILED);

		PresentableResponse response = makeResponse();		
		presentNavigationResponse(response);
		ApplicationContext.executedUseCases.push(this);
	}

	private void setOutcome(ActionOutcome outcome) {
		this.outcome = outcome;
	}

	private PresentableResponse makeResponse() {
		return (outcome == ActionOutcome.SUCCEEDED) 
				? makePresentableFormComponent() : makePresentableResponse();
	}

	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires an answer.";
	}

	private void executeMove(Direction direction) {
		new InMemoryTransporter().move(direction);
	}

	private void presentNavigationResponse(PresentableResponse presentableResponse) {
		getPresenterFromContext(presentableResponse.outcome).
		present(presentableResponse);
	}

	private Presenter getPresenterFromContext(ActionOutcome outcome) {
		if (outcome == ActionOutcome.SUCCEEDED)
			return ApplicationContext.formComponentPresenter;
		else
			return ApplicationContext.responsePresenter;
	}

	//	TODO:	Navigation should know nothing about PresentableResponses.
	//			Make some factories.	
	private PresentableResponse makePresentableResponse() {
		PresentableResponse result = new PresentableResponse();
		result.message = getAnswerRequiredMessage();
		result.outcome = ActionOutcome.FAILED;
		return result;
	}

	private PresentableResponse makePresentableFormComponent() {		
		FormComponent current = getCurrentFormComponent();
		PresentableFormComponent result = new PresentableFormComponent();
		result.question = makePresentableQuestion(current.question);
		result.answer = makePresentableAnswer(current.answer);
		result.outcome = ActionOutcome.SUCCEEDED;
		result.message = result.question.message;
		return result;
	}

	private FormComponent getCurrentFormComponent() {
		return ApplicationContext.formComponentState.getCurrent();
	}

	private PresentableQuestion makePresentableQuestion(Prompt requestedQuestion) {
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
		if (outcome != ActionOutcome.SUCCEEDED) return;
		Direction direction = getUndoDirection();
		executeMove(direction);
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
