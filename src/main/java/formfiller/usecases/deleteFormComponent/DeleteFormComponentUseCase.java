package formfiller.usecases.deleteFormComponent;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Outcome;
import formfiller.request.models.DeleteFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableResponse;
import formfiller.usecases.undoable.UndoableUseCase;

public class DeleteFormComponentUseCase implements UndoableUseCase {
	private Outcome outcome = Outcome.NEUTRAL;
	private String message = "";
	private String componentId;

	public void execute(Request request) {		
		DeleteFormComponentRequest castRequest = 
				(DeleteFormComponentRequest) request;
		componentId = castRequest.componentId;
		deleteComponent();
		PresentableResponse response = makeResponse();		
		presentResponse(response);
		addToExecutedUseCases();
	}

	private void deleteComponent() {
		FormComponent deleted = 
				FormFillerContext.formComponentGateway.remove(componentId);
		if (deleted == null) throw new AbsentElementDeletion(
					"No component existed with id " + componentId);
		handleSuccessfulUseCase("You successfully deleted the form component, " + 
				makeQuotedComponentId());					
	}

	private void handleSuccessfulUseCase(String message) {
		outcome = Outcome.POSITIVE;
		this.message = message;
	}

	//	TODO:	Fix duplication in AddFormComponentUseCase
	private PresentableResponse makeResponse() {
		PresentableResponse result = new PresentableResponse();
		result.message = message;
		result.outcome = outcome;
		return result;
	}
	
	private void presentResponse(PresentableResponse presentableResponse) {
		FormFillerContext.outcomePresenter.present(presentableResponse);
	}

	private String makeQuotedComponentId() {
		String result = "\"" + componentId + ".\"";
		return result;
	}

	private void addToExecutedUseCases() {
		FormFillerContext.executedUseCases.add(this);
	}

	//	TODO:	Implement
	public void undo() { }
	
	public class AbsentElementDeletion extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AbsentElementDeletion(String message) {
			super(message);
		} 
	}
}
