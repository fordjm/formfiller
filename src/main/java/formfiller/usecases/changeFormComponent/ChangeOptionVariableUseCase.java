package formfiller.usecases.changeFormComponent;

import formfiller.entities.answerFormat.OptionVariable;
import formfiller.request.models.ChangeOptionVariableRequest;
import formfiller.request.models.Request;
import formfiller.utilities.StringUtilities;

public class ChangeOptionVariableUseCase extends ChangeFormat {
	protected ChangeOptionVariableRequest castRequest;
	
	public ChangeOptionVariableUseCase() {
		newFormat = new OptionVariable();
	}
	
	protected void castRequest(Request request) {
		castRequest = (ChangeOptionVariableRequest) request;
	}
	
	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId);
	}
	
	protected void assignInstanceVariables() {
		id  = castRequest.componentId;
	}

}
