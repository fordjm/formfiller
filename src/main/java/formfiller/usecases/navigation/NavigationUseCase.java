package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.boundaryCrossers.PresentableResponseImpl;
import formfiller.entities.ExecutedUseCaseImpl;
import formfiller.enums.ActionOutcome;
import formfiller.gateways.NavigationValidator;
import formfiller.gateways.Transporter;
import formfiller.gateways.Transporter.Direction;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

public class NavigationUseCase implements UseCase {
	private ActionOutcome outcome;
	private String message;

	private NavigationValidator getNavigator(){
		return getTransporter().navigationValidator;
	}
	
	private Transporter getTransporter(){
		return ApplicationContext.formComponentGateway.transporter;
	}
	
	public void execute(Request request) {
		if (request == null) throw new NullExecution();
		
		NavigationRequest navigationRequest = (NavigationRequest) request;
		Transporter.Direction direction = navigationRequest.getDirection();
		NavigationValidator navigator = getNavigator();
		
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
		ApplicationContext.formComponentGateway.transporter.move(direction);
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
