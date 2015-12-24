package formfiller.usecases.undoable;

import formfiller.Context;
import formfiller.enums.Outcome;
import formfiller.request.models.Request;
import formfiller.response.models.NotificationResponseModel;

public abstract class UndoableUseCaseExecution implements UndoableUseCase {
	protected Outcome outcome = Outcome.NEUTRAL;
	protected String message = "";

	public void execute(Request request) {
		castRequest(request);
		checkForMalformedRequest();
		execute();		
		handleSuccessfulUseCase();
		NotificationResponseModel response = makeResponse();		
		presentResponse(response);
	}

	protected abstract void castRequest(Request request);

	protected void checkForMalformedRequest() {
		if (isRequestMalformed()) throw new MalformedRequest();
	}

	protected abstract boolean isRequestMalformed();

	protected abstract void execute();

	protected void handleSuccessfulUseCase() {
		outcome = Outcome.POSITIVE;
		this.message = makeSuccessfulMessage();
		addToExecutedUseCases();
	}

	protected void addToExecutedUseCases() {
		Context.executedUseCases.add(this);
	}

	protected abstract String makeSuccessfulMessage();
	
	protected NotificationResponseModel makeResponse() {
		NotificationResponseModel result = new NotificationResponseModel();
		result.message = message;
		return result;
	}
	
	protected void presentResponse(NotificationResponseModel presentableResponse) {
		Context.outcomePresenter.present(presentableResponse);
	}
	
	protected void ensureUseCaseStateIsUndoable() {
		if (outcome != Outcome.POSITIVE) throw new UnsuccessfulUseCaseUndo();
	}

	public class MalformedRequest extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
	
	public class UnsuccessfulUseCaseUndo extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; }
}