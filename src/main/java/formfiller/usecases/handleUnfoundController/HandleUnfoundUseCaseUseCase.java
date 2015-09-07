package formfiller.usecases.handleUnfoundController;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.UseCase;
import formfiller.enums.Outcome;
import formfiller.request.models.*;
import formfiller.response.models.PresentableResponse;

public class HandleUnfoundUseCaseUseCase implements UseCase {	

	public void execute(Request request) {
		if (request == null) request = new HandleUnfoundUseCaseRequest();
		
		HandleUnfoundUseCaseRequest handleUnfoundUseCaseRequest = 
				(HandleUnfoundUseCaseRequest) request;
		String message = handleUnfoundUseCaseRequest.message;
		FormFillerContext.responsePresenter.
				present(makeHandleUnfoundUseCaseResponse(message));
	}
	
	private PresentableResponse makeHandleUnfoundUseCaseResponse(String message) {
		PresentableResponse result = new PresentableResponse();
		result.message = message;
		result.outcome = Outcome.NEGATIVE;
		return result;
	}	
}