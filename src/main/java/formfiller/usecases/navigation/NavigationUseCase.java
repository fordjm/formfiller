package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.boundaryCrossers.PresentableFormComponent;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.entities.Answer;
import formfiller.entities.ExecutedUseCaseImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.ActionOutcome;
import formfiller.gateways.NavigationValidator;
import formfiller.gateways.Transporter;
import formfiller.gateways.InMemoryTransporter;
import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

public class NavigationUseCase implements UseCase {
	private ActionOutcome outcome;
	private String message;

	private NavigationValidator getNavigationValidator(){
		return getTransporter().getNavigationValidator();
	}
	
	private Transporter getTransporter(){
		return ApplicationContext.formComponentGateway.getTransporter();
	}
	
	public void execute(Request request) {
		if (request == null) throw new NullExecution();
		
		NavigationRequest navigationRequest = (NavigationRequest) request;
		InMemoryTransporter.Direction direction = navigationRequest.getDirection();
		NavigationValidator navigator = getNavigationValidator();
		
		if (navigator.isMoveLegal(direction)) {
			executeMove(direction);
			setOutcome(ActionOutcome.SUCCEEDED);
		} else
			setOutcome(ActionOutcome.FAILED);
		setMessage();
		
		presentNavigationResponse();
		ApplicationContext.executedUseCases.push(
				new ExecutedUseCaseImpl(this, outcome, message));
	}
	
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires a response.";
	}
	
	private void setOutcome(ActionOutcome actionOutcome) {
		outcome = actionOutcome;
	}	
	
	private void setMessage() {
		if (this.outcome == ActionOutcome.SUCCEEDED || 
				this.outcome == ActionOutcome.NO_OUTCOME)
			this.message = "";
		else
			this.message = getAnswerRequiredMessage();
	}

	private void executeMove(Direction direction) {
		ApplicationContext.formComponentGateway.getTransporter().move(direction);
	}

	private void presentNavigationResponse() {
		PresentableResponse presentableResponse = makePresentableResponse();
		ApplicationContext.navigationPresenter.present(presentableResponse);
	}

	//	TODO:	Navigation should know nothing about PresentableResponses.
	//			Make some factories.	
	private PresentableResponse makePresentableResponse() {
		PresentableResponse result;
		if (outcome == ActionOutcome.FAILED)
			result = new PresentableResponse();
		else
			result = makePresentableFormComponent();
		result.message = message;
		result.outcome = outcome;
		return result;
	}
	
	private PresentableResponse makePresentableFormComponent() {
		PresentableFormComponent result = new PresentableFormComponent();
		FormComponent current = ApplicationContext.formComponentGateway.
				getTransporter().getCurrent();
		PresentableQuestion question = makePresentableQuestion(current.question);
		PresentableAnswer answer = makePresentableAnswer(current.answer);
		result.question = question;
		result.answer = answer;
		return result;
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
	
	@SuppressWarnings("serial")
	public class NullExecution extends RuntimeException { }
}
