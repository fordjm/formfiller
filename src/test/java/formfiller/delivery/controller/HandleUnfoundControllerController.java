package formfiller.delivery.controller;

import java.util.HashMap;

import formfiller.boundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.eventParser.ParsedEvent;
import formfiller.request.builder.RequestBuilder;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.interfaces.Request;
import formfiller.usecases.UseCaseFactory;
import formfiller.usecases.UseCaseFactoryImpl;

public class HandleUnfoundControllerController implements Controller {

	public void handle(ParsedEvent parsedUserRequest) {
		String message = unfoundControllerMessage(parsedUserRequest);
		Request request = handleUnfoundControllerRequest(message);
		UseCase useCase = handleUnfoundControllerUseCase(parsedUserRequest);
		useCase.execute(request);
	}
	private String unfoundControllerMessage(ParsedEvent parsedUserRequest) {
		String requestedMethod = requestedMethod(parsedUserRequest);
		return "Request " + requestedMethod + "was not found.";
	}
	private String requestedMethod(ParsedEvent parsedUserRequest){
		String result = "";
		String method = parsedUserRequest.getMethod();
		if (method.length() > 0)
			result += method + " ";		
		return result;
	}
	protected Request handleUnfoundControllerRequest(String message){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("handleUnfoundController", 
				makeArgsHashmap(message));
		return result;
	}
	protected UseCase handleUnfoundControllerUseCase(ParsedEvent parsedUserRequest){		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		return useCaseFactory.make("handleUnfoundController");
	}
	// TODO:  Move duplicate code to Helper class with generic parameter.
	private HashMap makeArgsHashmap(String message){
		HashMap result = new HashMap();
		result.put("message", message);
		return result;
	}

}
