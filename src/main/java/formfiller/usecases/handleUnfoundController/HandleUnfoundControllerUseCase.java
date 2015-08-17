package formfiller.usecases.handleUnfoundController;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableHandleUnfoundController;
import formfiller.enums.ActionOutcome;
import formfiller.usecases.Request;
import formfiller.usecases.UseCase;

public class HandleUnfoundControllerUseCase implements UseCase {	

	public void execute(Request request) {
		HandleUnfoundControllerRequest handleUnfoundControllerRequest = 
				(HandleUnfoundControllerRequest) request;
		String message = handleUnfoundControllerRequest.getMessage();
		ApplicationContext.handleErrorResponseBoundary.
				setPresentableResponse(makePresentableUnfoundControllerResponse(message));
	}
	
	private PresentableHandleUnfoundController makePresentableUnfoundControllerResponse(
			String message) {
		return makePresentableUnfoundControllerResponse(ActionOutcome.FAILED, message);
	}
	private PresentableHandleUnfoundController makePresentableUnfoundControllerResponse(
			ActionOutcome outcome, String message) {
		return new PresentableHandleUnfoundController(outcome, message);
	}	

}
