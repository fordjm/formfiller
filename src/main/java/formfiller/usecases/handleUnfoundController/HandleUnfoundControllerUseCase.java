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
		ApplicationContext.handleUnfoundControllerPresenter.
				present(makePresentableUnfoundControllerResponse(message));
	}
	
	private PresentableHandleUnfoundController makePresentableUnfoundControllerResponse(
			String message) {
		return makePresentableUnfoundControllerResponse(ActionOutcome.FAILED, message);
	}
	private PresentableHandleUnfoundController makePresentableUnfoundControllerResponse(
			ActionOutcome outcome, String message) {
		PresentableHandleUnfoundController result = new PresentableHandleUnfoundController();
		result.setMessage(message);
		result.setOutcome(outcome);
		return result;
	}	

}
