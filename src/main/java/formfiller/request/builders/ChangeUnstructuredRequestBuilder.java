package formfiller.request.builders;

import formfiller.request.models.ChangeFormatRequest;
import formfiller.request.models.Request;

public class ChangeUnstructuredRequestBuilder implements RequestBuilderFunctions {
	private ChangeFormatRequest request;

	public ChangeUnstructuredRequestBuilder() {
		request = new ChangeFormatRequest();
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
