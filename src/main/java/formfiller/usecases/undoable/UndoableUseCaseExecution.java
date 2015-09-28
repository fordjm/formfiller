package formfiller.usecases.undoable;

import formfiller.FormFillerContext;
import formfiller.enums.Outcome;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableResponse;

public abstract class UndoableUseCaseExecution implements UndoableUseCase {
	protected Outcome outcome = Outcome.NEUTRAL;
	protected String message = "";

	public void execute(Request request) {
		castRequest(request);
		checkForMalformedRequest();
		assignInstanceVariables();
		execute();		
		handleSuccessfulUseCase();
		PresentableResponse response = makeResponse();		
		presentResponse(response);
	}

	protected abstract void castRequest(Request request);

	protected void checkForMalformedRequest() {
		if (isRequestMalformed())
			throw new MalformedRequest();
	}

	protected abstract boolean isRequestMalformed();

	protected abstract void assignInstanceVariables();

	protected abstract void execute();

	protected void handleSuccessfulUseCase() {
		outcome = Outcome.POSITIVE;
		this.message = makeSuccessfulMessage();
		addToExecutedUseCases();
	}

	protected abstract String makeSuccessfulMessage();
	
	protected PresentableResponse makeResponse() {
		PresentableResponse result = new PresentableResponse();
		result.message = message;
		result.outcome = outcome;
		return result;
	}
	
	protected void presentResponse(PresentableResponse presentableResponse) {
		FormFillerContext.outcomePresenter.present(presentableResponse);
	}

	protected void addToExecutedUseCases() {
		FormFillerContext.executedUseCases.add(this);
	}
	
	protected void ensureUseCaseIsUndoable() {
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