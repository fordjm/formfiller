package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.boundaryCrossers.PresentableResponseImpl;
import formfiller.entities.ExecutedUseCaseImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.ActionOutcome;
import formfiller.gateways.Navigator;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

public class NavigationUseCase implements UseCase {
	private ActionOutcome outcome;
	private String message;
	
	public void execute(Request request) {
		if (request == null) throw new NullExecution();
		NavigationRequest navigationRequest = (NavigationRequest) request;
		navigateByIndexOffset(navigationRequest.getOffset());
		ApplicationContext.executedUseCases.push(
				new ExecutedUseCaseImpl(this, outcome, message));
	}
	
	private Navigator.Direction getNavigatorDirection(int indexOffset){
		if (indexOffset > 0) return Navigator.Direction.FORWARD;
		else if (indexOffset == 0) return Navigator.Direction.IN_PLACE;
		else return Navigator.Direction.BACKWARD;
	} 
	
	//	TODO:	Move to NavigationValidator
	private void navigateByIndexOffset(int indexOffset) {	
		PresentableResponse presentableResponse;
		if (isIndexAdvancing(indexOffset) && isAnswerRequiredButAbsent()){
			setOutcome(ActionOutcome.FAILED);
			setMessage(getAnswerRequiredMessage());
		}
		else{
			setOutcome(ActionOutcome.SUCCEEDED);	
			Navigator.Direction direction = getNavigatorDirection(indexOffset);
			ApplicationContext.formComponentGateway.navigator.move(direction);
			setMessage("");
		}
		presentableResponse = makePresentableNavigation();
		ApplicationContext.navigationPresenter.present(presentableResponse);
	}
	
	private boolean isIndexAdvancing(int indexOffset) {
		return indexOffset > 0;
	}
	
	private boolean isAnswerRequiredButAbsent(){
		boolean result;
		Prompt currentQuestion = getCurrentQuestion();
		result = currentQuestion.requiresAnswer() && 
				!currentQuestion.hasAnswer();
		return result;
	}
	
	private Prompt getCurrentQuestion() {
		return getCurrentFormComponent().question;
	}
	
	private FormComponent getCurrentFormComponent() {
		return ApplicationContext.formComponentGateway.navigator.getCurrent();
	}
	
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires a response.";
	}
	
	private void setOutcome(ActionOutcome actionOutcome) {
		outcome = actionOutcome;
	}	
	
	private void setMessage(String message) {
		this.message = message;
	}
	
	private PresentableResponse makePresentableNavigation() {
		PresentableResponse result = new PresentableResponseImpl();
		result.setMessage(message);
		result.setOutcome(outcome);
		return result;
	}
	
	public class NullExecution extends RuntimeException { }
}
