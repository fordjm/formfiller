package formfiller.request.builders;

import formfiller.request.models.RequestWithComponentId;
import formfiller.request.models.Request;

public class DeleteFormComponentRequestBuilder implements RequestBuilderFunctions{
	private RequestWithComponentId request;
	
	public DeleteFormComponentRequestBuilder() {
		request = new RequestWithComponentId();
	}

	public void buildComponentId(String componentId) {
		request.componentId = componentId;
	}

	public void buildName() {
		request.name = "DeleteFormComponent";
	}

	public Request getRequest() {
		return request;
	}
}
