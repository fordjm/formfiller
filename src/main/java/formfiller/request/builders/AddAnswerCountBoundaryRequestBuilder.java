package formfiller.request.builders;

import formfiller.request.models.AddAnswerCountBoundaryRequest;
import formfiller.request.models.Request;

public class AddAnswerCountBoundaryRequestBuilder implements RequestBuilderFunctions {
	private AddAnswerCountBoundaryRequest request;
	
	public AddAnswerCountBoundaryRequestBuilder() {
		request = new AddAnswerCountBoundaryRequest();
	}
	
	public void buildName() {
		request.name = "AddAnswerCountBoundary";
	}

	public void buildComponentId(String componentId) {
		request.componentId = componentId;
	}

	public void buildBoundary(String boundary) {
		request.boundary = boundary;
	}

	public void buildCount(Integer count) {
		request.count = count;
	}

	public Request getRequest() {
		return request;
	}

}
