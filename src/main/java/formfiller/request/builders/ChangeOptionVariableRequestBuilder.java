package formfiller.request.builders;

import formfiller.request.models.ChangeOptionVariableRequest;
import formfiller.request.models.Request;

public class ChangeOptionVariableRequestBuilder implements RequestBuilderFunctions {
	ChangeOptionVariableRequest request;

	public ChangeOptionVariableRequestBuilder() {
		request = new ChangeOptionVariableRequest();
	}
	
	public void buildComponentId(String componentId) {
		request.componentId = componentId;
	}

	public void buildName() {
		request.name = "ChangeOptionVariable";
	}

	public Request getRequest() {
		return request;
	}

}
