package formfiller.delivery.controller;

import formfiller.boundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactory;
import formfiller.usecases.factory.UseCaseFactoryImpl;

public class HandleUnfoundControllerController implements Controller {

	public void handle(ParsedEvent parsedUserRequest) {
		String message = makeUnfoundControllerMessage(parsedUserRequest.method);
		Request request = makeHandleUnfoundControllerRequest(message);
		UseCase useCase = makeHandleUnfoundControllerUseCase();
		useCase.execute(request);
	}
	
	private String makeUnfoundControllerMessage(String method) {
		String requestedMethod = addWhitespaceIfNecessary(method);
		return "Request " + requestedMethod + "was not found.";
	}
	
	private String addWhitespaceIfNecessary(String method){
		String result = "";
		if (method.length() > 0)
			result += method + " ";		
		return result;
	}
	
	protected Request makeHandleUnfoundControllerRequest(String message){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("handleUnfoundController", 
				ArgsMaker.makeArgs("message", message));	// TODO:	Wrap boundary interface.
		return result;
	}
	
	protected UseCase makeHandleUnfoundControllerUseCase(){		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		return useCaseFactory.make("handleUnfoundController");
	}
}
