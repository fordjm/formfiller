package formfiller.usecases.navigation;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.Presenter;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.ConstrainableAnswer;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.Outcome;
import formfiller.enums.Direction;
import formfiller.gateways.InMemoryTransporter;
import formfiller.request.models.NavigationRequest;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableAnswer;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableQuestion;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.PresenterSelector;

//	TODO:	Extract UndoableUseCase abstraction.
public class NavigationUseCase implements UseCase, Undoable {
	private Direction direction = Direction.NONE;
	private Outcome outcome = Outcome.NEUTRAL;

	public void execute(Request request) {		
		if (request == null) return;

		NavigationRequest navigationRequest = (NavigationRequest) request;
		direction = navigationRequest.direction;

		if (NavigationValidator.isValidDirectionalMove(direction)) {
			setOutcome(Outcome.POSITIVE);
			executeDirectionalMove(direction);
		} else 
			setOutcome(Outcome.NEGATIVE);

		PresentableResponse response = makeResponse();		
		presentResponse(response);
		FormFillerContext.executedUseCases.push(this);
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

	private void executeDirectionalMove(Direction direction) {
		new InMemoryTransporter().moveInDirection(direction);
	}

	private void presentResponse(PresentableResponse presentableResponse) {
		Presenter presenter = 
				PresenterSelector.selectPresenter(presentableResponse.outcome);
		presenter.present(presentableResponse);
	}	

	//	TODO:	Navigation should know nothing about PresentableResponses.
	//			Make some factories.	
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
		result.message = result.question.message;
		return result;
	}

	private FormComponent getCurrentFormComponent() {
		return FormFillerContext.formComponentState.getCurrent();
	}

	private PresentableQuestion makePresentableQuestion(Prompt requestedQuestion) {
		PresentableQuestion result = new PresentableQuestion();
		result.id = requestedQuestion.getId();
		result.message = requestedQuestion.getContent();
		return result;
	}

	private PresentableAnswer makePresentableAnswer(ConstrainableAnswer requestedAnswer) {
		PresentableAnswer result = new PresentableAnswer();
		result.id = requestedAnswer.getId();
		result.message = requestedAnswer.getContent().toString();
		return result;
	}

	public void undo() {
		if (outcome != Outcome.POSITIVE) return;
		Direction direction = getUndoDirection();
		executeDirectionalMove(direction);
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
