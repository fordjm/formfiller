package formfiller.usecases.changeFormComponent;

import fitnesse.request.models.ChangeIdRequest;
import formfiller.FormFillerContext;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableResponse;

public class ChangeIdUseCase extends ChangeFormComponent {
	private ChangeIdRequest castRequest;
	private String newId = "";
	
	protected void castRequest(Request request) {
		castRequest = (ChangeIdRequest) request;
	}

	protected void assignInstanceVariables() {
		id = castRequest.oldId;
		newId = castRequest.newId;
	}

	//	TODO:	Fix fragile duplicated value.
	protected void change(FormComponent component) {
		FormFillerContext.formComponentGateway.remove(id);
		component.question.id = newId;
		component.id = newId;
		FormFillerContext.formComponentGateway.save(component);
	}

	protected PresentableResponse makeResponse() {
		PresentableResponse result = new PresentableResponse();
		result.message = "You successfully changed the id from " + 
				makeQuotedString(id) + " to " + makeQuotedString(newId);
		return result;
	}

	//	TODO:	Extract to abstract class
	protected void presentResponse(PresentableResponse response) {
		FormFillerContext.outcomePresenter.present(response);
	}

	//	TODO:	Extract to utilities class
	private String makeQuotedString(String input) {
		String result = "\""+ input + "\"";
		return result;
	}

	//	TODO:	Extract to abstract class
	protected void addToExecutedUseCases() {
		FormFillerContext.executedUseCases.add(this);
	}

	//	TODO:	Implement
	public void undo() { }
}
