package formfiller.usecases.deleteFormComponent;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.DeleteFormComponentRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.StringUtilities;

public class DeleteFormComponentUseCase extends UndoableUseCaseExecution {
	private DeleteFormComponentRequest castRequest;
	private String componentId = "";
	private FormComponent deleted;

	protected void castRequest(Request request) {
		castRequest = (DeleteFormComponentRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId);
	}

	protected void assignInstanceVariables() {
		componentId = castRequest.componentId;
	}

	protected void execute() {
		deleted = FormFillerContext.formComponentGateway.remove(componentId);
		if (deleted == null) throw new AbsentElementDeletion(
					"No component existed with id " + componentId);					
	}

	protected String makeSuccessfulMessage() {
		return "You successfully deleted the form component, " + 
				StringUtilities.makeQuotedString(componentId);
	}

	public void undo() {
		ensureUseCaseIsUndoable();
		FormFillerContext.formComponentGateway.save(deleted);
	}
	
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
