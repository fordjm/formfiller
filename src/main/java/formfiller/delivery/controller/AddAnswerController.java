package formfiller.delivery.controller;

import java.util.List;

import formfiller.Context;
import formfiller.appBoundaries.UseCase;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.builder.RequestBuilder;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.utilities.StringUtilities;

public class AddAnswerController extends UndoableUseCaseController {
	private String content;
	private Arguments arguments;

	public void handle(ParsedEvent parsedEvent) {
		String parameters = StringUtilities.makeSpacedString(
				parsedEvent.parameters.toArray(new String[0]));
		content = StringUtilities.makeQuotedString(parameters);
		arguments = makeArguments();
		Request request = makeAddAnswerRequest();
		UseCase useCase = makeAddAnswerUseCase();
		
		useCase.execute(request);
	}

	protected Arguments makeArguments() {
		Arguments arguments = new Arguments();
		arguments.add("componentId", getCurrentId());
		arguments.add("content", content);
		return arguments;
	}
	
	private Object getCurrentId() {
		FormComponent current = Context.formComponentState.getCurrent();
		return current.id;
	}

	private Request makeAddAnswerRequest() {
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("addAnswer", arguments);
		return result;
	}
	
	protected UseCase makeAddAnswerUseCase(){		
		UseCaseFactoryImpl factory = new UseCaseFactoryImpl();
		return factory.make("addAnswer");
	}

	@Override
	protected String getName() {
		return "AddAnswer";
	}

	@Override
	protected void assignRequiredParameters(List<String> parameters) {
		// TODO Auto-generated method stub
		
	}

}
