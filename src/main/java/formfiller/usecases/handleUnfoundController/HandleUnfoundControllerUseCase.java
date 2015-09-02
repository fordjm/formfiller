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
				present(makeHandleUnfoundControllerResponse(message));
	}
	
	private PresentableResponse makeHandleUnfoundControllerResponse(String message) {
		PresentableResponse result = new PresentableResponse();
		result.message = message;
		result.outcome = ActionOutcome.FAILED;
		return result;
	}	
}
