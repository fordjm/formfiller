package formfiller.delivery.controller;

import formfiller.appBoundaries.InputBoundary;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactory;
import formfiller.usecases.factory.UseCaseFactoryImpl;

public class HandleUnfoundUseCaseController implements Controller {

	public void handle(ParsedEvent parsedEvent) {
		if (parsedEvent == null) parsedEvent = makeNullParsedEvent();
		
		String message = makeUnfoundUseCaseMessage(parsedEvent.method);
		Arguments arguments = makeArguments(message);
		Request request = makeHandleUnfoundUseCaseRequest(arguments);
		InputBoundary useCase = makeHandleUnfoundUseCaseUseCase();
		
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
		Request result = requestBuilder.build("handleUnfoundUseCase", arguments);
		return result;
	}

	private Arguments makeArguments(String message) {
		Arguments arguments = new Arguments();
		arguments.add("message", message);
		return arguments;
	}
	
	protected InputBoundary makeHandleUnfoundUseCaseUseCase(){		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl();
		return useCaseFactory.make("handleUnfoundUseCase");
	}
}
