package formfiller.delivery.controller;

import formfiller.appBoundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.enums.QuestionAsked;
import formfiller.request.builder.RequestBuilder;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.askQuestion.LocalAskQuestionUseCase;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.utilities.*;

/**
 * AskQuestionController handles Ask Question events from the UI.
 * It creates an new Arguments object from the parsed event, gets a 
 * new request from the request builder, gets a new use case from the 
 * use case factory, and executes the use case.
 */
public class AskQuestionController implements Controller {
	private QuestionAsked which;
	private Arguments arguments;

	public void handle(ParsedEvent parsedEvent) {
		String questionAsked = parsedEvent.parameters.get(0);
		which = WhichQuestionParser.parseWhich(questionAsked);
		arguments = makeArguments();
		Request request = makeAskQuestionRequest();
		UseCase useCase = makeAskQuestionUseCase();
		LocalAskQuestionUseCase local = new LocalAskQuestionUseCase((AskQuestionUseCase) useCase);
		
		local.execute(request);
	}	
	
	protected Request makeAskQuestionRequest(){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("askQuestion", arguments);
		return result;
	}

	//	TODO:  Use reflection to add fieldNameOf(param).toString(), valueOf(param)?
	private Arguments makeArguments() {
		Arguments arguments = new Arguments();
		arguments.add("which", which);
		return arguments;
	}
	
	protected UseCase makeAskQuestionUseCase(){		
		UseCaseFactoryImpl factory = new UseCaseFactoryImpl();
		return factory.make("askQuestion");
	}
}
