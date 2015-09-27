package formfiller.usecases.changeFormComponent;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;

public abstract class ChangeFormComponent extends UndoableUseCaseExecution {
	protected String id = "";

	protected void execute() {
		FormComponent found = FormFillerContext.formComponentGateway.find(id);
		if (!FormComponentUtilities.isComponentNull(found))
			change(found);
	}

	protected abstract void change(FormComponent component);
}
