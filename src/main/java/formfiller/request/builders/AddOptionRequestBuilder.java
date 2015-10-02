package formfiller.request.builders;

import formfiller.request.models.AddOptionRequest;
import formfiller.request.models.Request;

public class AddOptionRequestBuilder implements RequestBuilderFunctions {
	private AddOptionRequest request;
	
	public AddOptionRequestBuilder() {
		request = new AddOptionRequest();
	}

	public void buildName() {
		request.name = "AddOption";
	}

	public void buildComponentId(String componentId) {
		request.componentId = componentId;
	}

	public void buildOption(String option) {
		request.option = option;
	}

	public Request getRequest() {
		return request;
	}

}
