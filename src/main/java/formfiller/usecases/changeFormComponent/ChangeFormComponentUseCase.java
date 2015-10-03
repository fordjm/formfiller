package formfiller.usecases.changeFormComponent;

import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;

public abstract class ChangeFormComponentUseCase extends UndoableUseCaseExecution {
	protected String id = "";

	protected void execute() {
		assignInstanceVariables();
		FormComponent found = Context.formComponentGateway.find(id);
		if (!FormComponentUtilities.isComponentNull(found)){
			createUndoInfo(found);
			change(found);			
		}
		else
			throw new AbsentFormComponentChange();
	}
	
	protected abstract void assignInstanceVariables();

	protected abstract void createUndoInfo(FormComponent found);

	protected abstract void change(FormComponent component);
	
	public class AbsentFormComponentChange extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L; }
}
