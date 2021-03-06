package formfiller.delivery.controller;

import formfiller.appBoundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.request.builder.RequestBuilder;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactory;
import formfiller.usecases.factory.UseCaseFactoryImpl;

/**
 * HandleUnfoundUseCaseController handles unknown events from the UI.
 * It creates an new Arguments object from the parsed event, gets a 
 * new request from the request builder, gets a new Use Case from the 
 * use case factory, and executes the use case.
 */
public class HandleUnfoundUseCaseController implements Controller {

	public void handle(ParsedEvent parsedEvent) {
		if (parsedEvent == null) parsedEvent = makeNullParsedEvent();
		
		String message = makeUnfoundUseCaseMessage(parsedEvent.method);
		Arguments arguments = makeArguments(message);
		Request request = makeHandleUnfoundUseCaseRequest(arguments);
		UseCase useCase = makeHandleUnfoundUseCaseUseCase();
		
		useCase.execute(request);
	}

	private ParsedEvent makeNullParsedEvent() {
		ParsedEvent result = new ParsedEvent();
		return result;
	}
	
	private String makeUnfoundUseCaseMessage(String method) {
		String requestedMethod = addWhitespaceIfNonEmpty(method);
		return "Request " + requestedMethod + "was not found.";
	}
	
	private String addWhitespaceIfNonEmpty(String input){
		String result = "";
		if (input.length() > 0)
			result += input + " ";		
		return result;
	}
	
	protected Request makeHandleUnfoundUseCaseRequest(Arguments arguments){
		RequestBuilder requestBuilder = new RequestBuilderImpl();		
		Request result = requestBuilder.build("HandleUnfoundUseCase", arguments);
		return result;
	}

	private Arguments makeArguments(String message) {
		Arguments arguments = new Arguments();
		arguments.add("message", message);
		return arguments;
	}
	
	protected UseCase makeHandleUnfoundUseCaseUseCase(){		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		return useCaseFactory.make("HandleUnfoundUseCase");
	}
}
