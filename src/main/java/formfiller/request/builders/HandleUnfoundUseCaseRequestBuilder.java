package formfiller.request.builders;

import formfiller.request.models.HandleUnfoundUseCaseRequest;
import formfiller.request.models.Request;

public class HandleUnfoundUseCaseRequestBuilder implements RequestBuilderFunctions {
	HandleUnfoundUseCaseRequest handleUnfoundUseCaseRequest;
	
	public HandleUnfoundUseCaseRequestBuilder(){
		handleUnfoundUseCaseRequest = new HandleUnfoundUseCaseRequest();
	}

	public void buildName() {
		handleUnfoundUseCaseRequest.name = "HandleUnfoundUseCase";
	}
	
	public void buildMessage(String message) {
		handleUnfoundUseCaseRequest.message = message;
	}
	
	public Request getRequest() {
		return handleUnfoundUseCaseRequest;
	}
}
