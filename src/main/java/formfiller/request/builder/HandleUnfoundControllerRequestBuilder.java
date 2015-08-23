package formfiller.request.builder;

import formfiller.request.implementations.HandleUnfoundControllerRequestImpl;
import formfiller.request.interfaces.HandleUnfoundControllerRequest;
import formfiller.request.interfaces.Request;

public class HandleUnfoundControllerRequestBuilder implements AbstractRequestBuilder {
	HandleUnfoundControllerRequest handleUnfoundControllerRequest;
	
	public HandleUnfoundControllerRequestBuilder(){
		handleUnfoundControllerRequest = new HandleUnfoundControllerRequestImpl();
	}

	public void buildName() {
		handleUnfoundControllerRequest.setName("HandleUnfoundRequest");
	}
	public void buildMessage(String message) {
		handleUnfoundControllerRequest.setMessage(message);
	}
	public Request getRequest() {
		return handleUnfoundControllerRequest;
	}

}
