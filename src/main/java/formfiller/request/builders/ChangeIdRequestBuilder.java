package formfiller.request.builders;

import formfiller.request.models.ChangeIdRequest;
import formfiller.request.models.Request;

public class ChangeIdRequestBuilder implements RequestBuilderFunctions {
	ChangeIdRequest request;
	
	public ChangeIdRequestBuilder() {
		request = new ChangeIdRequest();
	}

	public void buildName() {
		request.name = "ChangeId";
	}

	public void buildOldId(String oldId) {
		request.oldId = oldId;
	}

	public void buildNewId(String newId) {
		request.newId = newId;
	}

	public Request getRequest() {
		return request;
	}
}
