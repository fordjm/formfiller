package formfiller.request.builders;

import formfiller.request.models.DeleteFormComponentRequest;
import formfiller.request.models.Request;

public class DeleteFormComponentRequestBuilder implements RequestBuilderFunctions{
	private DeleteFormComponentRequest request;
	
	public DeleteFormComponentRequestBuilder() {
		request = new DeleteFormComponentRequest();
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
