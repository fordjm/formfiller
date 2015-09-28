package formfiller.usecases.changeFormComponent;

import formfiller.entities.answerFormat.Unstructured;
import formfiller.request.models.ChangeUnstructuredRequest;
import formfiller.request.models.Request;
import formfiller.utilities.StringUtilities;

public class ChangeUnstructuredUseCase extends ChangeFormat {
	protected ChangeUnstructuredRequest castRequest;
	
	public ChangeUnstructuredUseCase() {
		newFormat = new Unstructured();
	}
	
	protected void castRequest(Request request) {
		castRequest = (ChangeUnstructuredRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId);
	}

	protected void assignInstanceVariables() {
		id = castRequest.componentId;
	}
	
}
