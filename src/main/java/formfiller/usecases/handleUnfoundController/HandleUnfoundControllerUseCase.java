package formfiller.usecases.handleUnfoundController;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableHandleUnfoundController;
import formfiller.enums.ActionOutcome;
import formfiller.usecases.Request;

public class HandleUnfoundControllerUseCase implements UseCase {	

	public void execute(Request request) {
		HandleUnfoundControllerRequest handleUnfoundControllerRequest = 
				(HandleUnfoundControllerRequest) request;
		String message = handleUnfoundControllerRequest.getMessage();
		ApplicationContext.handleErrorPresenter.
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
