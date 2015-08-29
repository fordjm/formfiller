package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.boundaryCrossers.PresentableResponseImpl;
import formfiller.entities.ExecutedUseCaseImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.ActionOutcome;
import formfiller.gateways.Transporter;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

public class NavigationUseCase implements UseCase {
	private ActionOutcome outcome;
	private String message;
	
	public void execute(Request request) {
		if (request == null) throw new NullExecution();
		
		NavigationRequest navigationRequest = (NavigationRequest) request;
		int offset = navigationRequest.getOffset();
		if (isMoveLegal(offset)) {
			navigateByIndexOffset(offset);
			handleNavigationOutcome(ActionOutcome.SUCCEEDED);
		}
		else 
			handleNavigationOutcome(ActionOutcome.FAILED);
		presentNavigationResponse();
		ApplicationContext.executedUseCases.push(
				new ExecutedUseCaseImpl(this, outcome, message));
	}
	
	private Transporter.Direction getNavigatorDirection(int indexOffset){
		if (indexOffset > 0) return Transporter.Direction.FORWARD;
		else if (indexOffset == 0) return Transporter.Direction.IN_PLACE;
		else return Transporter.Direction.BACKWARD;
	} 
	
	private boolean isMoveLegal(int indexOffset) {
		return !isIndexAdvancing(indexOffset) || !isAnswerRequiredButAbsent();
	}
	
	private boolean isIndexAdvancing(int indexOffset) {
		return indexOffset > 0;
	}
	
	private boolean isAnswerRequiredButAbsent(){
		Prompt currentQuestion = getCurrentQuestion();
		boolean result = currentQuestion.requiresAnswer() && 
				!currentQuestion.hasAnswer();
		return result;
	}
	
	private Prompt getCurrentQuestion() {
		return getCurrentFormComponent().question;
	}
	
	private FormComponent getCurrentFormComponent() {
		return ApplicationContext.formComponentGateway.navigator.getCurrent();
	}
	
	private void handleNavigationOutcome(ActionOutcome actionOutcome) {
		setOutcome(actionOutcome);
		setMessage(this.outcome);
	}
	
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires a response.";
	}
	
	private void setOutcome(ActionOutcome actionOutcome) {
		outcome = actionOutcome;
	}	
	
	private void setMessage(ActionOutcome outcome) {
		if (outcome == ActionOutcome.SUCCEEDED || 
					outcome == ActionOutcome.NO_OUTCOME)
			this.message = "";
		else
			this.message = getAnswerRequiredMessage();
	}

	private void navigateByIndexOffset(int indexOffset) {
		Transporter.Direction direction = getNavigatorDirection(indexOffset);
		ApplicationContext.formComponentGateway.navigator.move(direction);
	}

	//	TODO:	Successful navigation presentation is current form component.
	private void presentNavigationResponse() {
		PresentableResponse presentableResponse = makePresentableNavigation();
		ApplicationContext.navigationPresenter.present(presentableResponse);
	}
	
	private PresentableResponse makePresentableNavigation() {
		PresentableResponse result = new PresentableResponseImpl();
		result.setMessage(message);
		result.setOutcome(outcome);
		return result;
	}
	
	public class NullExecution extends RuntimeException { }
}
