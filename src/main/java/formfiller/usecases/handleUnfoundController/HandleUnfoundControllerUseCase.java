package formfiller.usecases.handleUnfoundController;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.enums.ActionOutcome;
import formfiller.request.interfaces.HandleUnfoundControllerRequest;
import formfiller.request.interfaces.Request;

public class HandleUnfoundControllerUseCase implements UseCase {	

	public void execute(Request request) {
		HandleUnfoundControllerRequest handleUnfoundControllerRequest = 
				(HandleUnfoundControllerRequest) request;
		String message = handleUnfoundControllerRequest.getMessage();
		ApplicationContext.handleUnfoundControllerPresenter.
				present(makePresentableUnfoundControllerResponse(message));
	}
	
	private PresentableResponse makePresentableUnfoundControllerResponse(
			String message) {
		return makePresentableUnfoundControllerResponse(ActionOutcome.FAILED, message);
	}
	private PresentableResponse makePresentableUnfoundControllerResponse(
			ActionOutcome outcome, String message) {
		PresentableResponse result = new PresentableResponse();
		result.message = message;
		result.outcome = outcome;
		return result;
	}	

}
