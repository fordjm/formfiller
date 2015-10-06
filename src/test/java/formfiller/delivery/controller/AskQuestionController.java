package formfiller.delivery.controller;

import formfiller.appBoundaries.InputBoundary;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.enums.QuestionAsked;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.utilities.*;

public class AskQuestionController implements Controller {
	private QuestionAsked which;
	private Arguments arguments;

	public void handle(ParsedEvent parsedEvent) {
		String questionAsked = parsedEvent.parameters.get(0);
		which = WhichQuestionParser.parseWhich(questionAsked);
		arguments = makeArguments();
		Request request = makeAskQuestionRequest();
		InputBoundary useCase = makeAskQuestionUseCase();
		
		useCase.execute(request);
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
	
	protected InputBoundary makeAskQuestionUseCase(){		
		UseCaseFactoryImpl factory = new UseCaseFactoryImpl();
		return factory.make("askQuestion");
	}
}
