package formfiller.usecases;

import formfiller.enums.Outcome;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableResponse;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.Context;
import formfiller.appBoundaries.UseCase;

public class LocalUseCase implements UseCase {
	private UndoableUseCase useCase;
	private Outcome outcome;
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
		outcome = Outcome.NEGATIVE;
		this.message = message;
		presentFailedResponse();
	}

	private void presentFailedResponse() {
		PresentableResponse response = makeResponse();		
		presentResponse(response);
	}

	private PresentableResponse makeResponse() {
		PresentableResponse result = new PresentableResponse();
		result.message = message;
		result.outcome = outcome;
		return result;
	}
	
	private void presentResponse(PresentableResponse presentableResponse) {
		Context.outcomePresenter.present(presentableResponse);
	}
}
