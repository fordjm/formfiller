package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.entities.Answer;
import formfiller.entities.ExecutedUseCaseImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.ActionOutcome;
import formfiller.gateways.NavigationValidator;
import formfiller.gateways.Transporter;
import formfiller.gateways.InMemoryTransporter.Direction;
import formfiller.request.models.NavigationRequest;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableAnswer;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableQuestion;
import formfiller.response.models.PresentableResponse;

public class NavigationUseCase implements UseCase {
	private ActionOutcome outcome = ActionOutcome.NONE;
	private String message = "";

	private NavigationValidator getNavigationValidator(){
		Transporter transporter = getTransporter();
		return transporter.getNavigationValidator();
	}
	
	private Transporter getTransporter(){
		return ApplicationContext.formComponentGateway.getTransporter();
	}
	
	public void execute(Request request) {
		if (request == null) throw new NullExecution();
		
		NavigationRequest navigationRequest = (NavigationRequest) request;
		Direction direction = navigationRequest.direction;
		NavigationValidator validator = getNavigationValidator();
		
		if (validator.isMoveLegal(direction)) {
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
		if (this.outcome == ActionOutcome.FAILED)
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
