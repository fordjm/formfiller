package formfiller.usecases.changeFormComponent;

import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableResponse;
import formfiller.usecases.undoable.UndoableUseCase;

public abstract class ChangeFormComponent implements UndoableUseCase {
	protected String id = "";

	public void execute(Request request) {
		castRequest(request);
		assignInstanceVariables();
		
		FormComponent found = FormFillerContext.formComponentGateway.find(id);
		if (!componentIsNull(found))
			change(found);
		
		PresentableResponse response = makeResponse();	
		presentResponse(response);
		addToExecutedUseCases();
	}

	//	TODO:	Extract to utilities class
	private boolean componentIsNull(FormComponent component) {
		return component.equals(NullFormComponents.NULL);
	}

	protected abstract void castRequest(Request request);

	protected abstract void assignInstanceVariables();

	protected abstract void change(FormComponent component);

	protected abstract PresentableResponse makeResponse();

	protected abstract void presentResponse(PresentableResponse response);

	protected abstract void addToExecutedUseCases();
}
