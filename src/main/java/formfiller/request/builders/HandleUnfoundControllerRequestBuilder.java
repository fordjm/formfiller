package formfiller.request.builders;

import formfiller.request.models.HandleUnfoundControllerRequest;
import formfiller.request.models.Request;

public class HandleUnfoundControllerRequestBuilder implements RequestBuilderFunctions {
	HandleUnfoundControllerRequest handleUnfoundControllerRequest;
	
	public HandleUnfoundControllerRequestBuilder(){
		handleUnfoundControllerRequest = new HandleUnfoundControllerRequest();
	}

	public void buildName() {
		handleUnfoundControllerRequest.name = "HandleUnfoundRequest";
	}
	
	public void buildMessage(String message) {
		handleUnfoundControllerRequest.message = message;
	}
	
	public Request getRequest() {
		return handleUnfoundControllerRequest;
	}
}
