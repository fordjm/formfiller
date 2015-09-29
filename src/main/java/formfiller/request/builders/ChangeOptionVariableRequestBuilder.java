package formfiller.request.builders;

import formfiller.request.models.ChangeFormatRequest;
import formfiller.request.models.Request;

public class ChangeOptionVariableRequestBuilder implements RequestBuilderFunctions {
	private ChangeFormatRequest request;

	public ChangeOptionVariableRequestBuilder() {
		request = new ChangeFormatRequest();
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
