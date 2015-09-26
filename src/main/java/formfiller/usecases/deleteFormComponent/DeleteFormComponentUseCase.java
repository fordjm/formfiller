package formfiller.usecases.deleteFormComponent;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.DeleteFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.usecases.addFormComponent.UndoableUseCaseExecution;
import formfiller.utilities.GeneralUtilities;

public class DeleteFormComponentUseCase extends UndoableUseCaseExecution {
	private DeleteFormComponentRequest castRequest;
	private String componentId = "";

	protected void castRequest(Request request) {
		castRequest = (DeleteFormComponentRequest) request;
	}

	protected boolean isRequestMalformed() {
		return castRequest.componentId == null || castRequest.componentId == "";
	}

	protected void assignInstanceVariables() {
		componentId = castRequest.componentId;
	}

	protected void execute() {
		FormComponent deleted = 
				FormFillerContext.formComponentGateway.remove(componentId);
		if (deleted == null) throw new AbsentElementDeletion(
					"No component existed with id " + componentId);					
	}

	protected String makeSuccessfulMessage() {
		return "You successfully deleted the form component, " + 
				GeneralUtilities.makeQuotedString(componentId);
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
