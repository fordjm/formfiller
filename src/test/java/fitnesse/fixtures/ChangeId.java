package fitnesse.fixtures;

import fitnesse.request.models.ChangeIdRequest;
import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.formComponent.NullFormComponents;
import formfiller.usecases.changeFormComponent.ChangeIdUseCase;

public class ChangeId {
	String oldId = ""; 
	String newId = "";
	
	public void whenTheUserChangesTheIdFromOldToNew(String oldId, String newId){
		this.oldId = oldId;
		this.newId = newId;
		executeUseCase();
	}

	private void executeUseCase() {
		ChangeIdRequest request = makeRequest();
		ChangeIdUseCase useCase = new ChangeIdUseCase();
		useCase.execute(request);
	}

	private ChangeIdRequest makeRequest() {
		ChangeIdRequest result = new ChangeIdRequest();
		result.oldId = oldId;
		result.newId = newId;
		return result;
	}
	
	public boolean foundComponent(String id){
		FormComponent foundComponent = 
				FormFillerContext.formComponentGateway.find(id);
		return !componentIsNull(foundComponent);
	}

	private boolean componentIsNull(FormComponent component) {
		return component.equals(NullFormComponents.NULL);
	}
}
