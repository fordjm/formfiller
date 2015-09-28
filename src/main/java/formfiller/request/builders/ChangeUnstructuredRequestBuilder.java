package formfiller.request.builders;

import formfiller.request.models.ChangeUnstructuredRequest;
import formfiller.request.models.Request;

public class ChangeUnstructuredRequestBuilder implements RequestBuilderFunctions {
	private ChangeUnstructuredRequest request;

	public ChangeUnstructuredRequestBuilder() {
		request = new ChangeUnstructuredRequest();
	}

	public void buildName() {
		request.name = "ChangeUnstructured";
	}

	public void buildComponentId(String componentId) {
		request.componentId = componentId;
	}
	
	public Request getRequest() { 
		return request;
	}
	
}
