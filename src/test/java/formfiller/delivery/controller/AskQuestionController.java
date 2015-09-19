package formfiller.delivery.controller;

import formfiller.appBoundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.enums.QuestionAsked;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.utilities.*;

public class AskQuestionController implements Controller {

	public void handle(ParsedEvent parsedEvent) {
		QuestionAsked which = WhichQuestionParser.parseWhich(parsedEvent.param);
		Arguments arguments = makeArguments(which);
		Request request = makeAskQuestionRequest(arguments);
		UseCase useCase = makeAskQuestionUseCase();
		
		useCase.execute(request);
	}	
	
	protected Request makeAskQuestionRequest(Arguments arguments){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("askQuestion", arguments);
		return result;
	}

	private Arguments makeArguments(QuestionAsked direction) {
		Arguments arguments = new Arguments();
		arguments.add("which", direction);
		return arguments;
	}
	
	protected UseCase makeAskQuestionUseCase(){		
		UseCaseFactoryImpl factory = new UseCaseFactoryImpl();
		return factory.make("askQuestion");
	}
}
