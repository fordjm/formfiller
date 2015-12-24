package formfiller.usecases;

import formfiller.request.models.Request;
import formfiller.response.models.NotificationResponseModel;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.Context;
import formfiller.appBoundaries.UseCase;

public class LocalUseCase implements UseCase {
	private UndoableUseCase useCase;
	private String message;

	public LocalUseCase(UndoableUseCase useCase) {
		this.useCase = useCase;
	}

	public void execute(Request request) {	
		if (request == null) request = Request.NULL;
		
		try {
			useCase.execute(request);
		} catch (ClassCastException cce){
			handleFailedUseCase("The request with name " + request.name + 
					" did not match the use case.");
		} catch (RuntimeException r){
			handleFailedUseCase(r.getMessage());
		}
	}

	private void handleFailedUseCase(String message) {
		this.message = message;
		presentFailedResponse();
	}

	private void presentFailedResponse() {
		NotificationResponseModel response = makeResponse();		
		presentResponse(response);
	}

	private NotificationResponseModel makeResponse() {
		NotificationResponseModel result = new NotificationResponseModel();
		result.message = message;
		return result;
	}
	
	private void presentResponse(NotificationResponseModel response) {
		Context.outcomePresenter.present(response);
	}
	
}
