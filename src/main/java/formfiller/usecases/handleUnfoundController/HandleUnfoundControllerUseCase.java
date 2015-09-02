package formfiller.usecases.handleUnfoundController;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.enums.ActionOutcome;
import formfiller.request.models.HandleUnfoundControllerRequest;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableResponse;

public class HandleUnfoundControllerUseCase implements UseCase {	

	public void execute(Request request) {
		HandleUnfoundControllerRequest handleUnfoundControllerRequest = 
				(HandleUnfoundControllerRequest) request;
		String message = handleUnfoundControllerRequest.message;
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
