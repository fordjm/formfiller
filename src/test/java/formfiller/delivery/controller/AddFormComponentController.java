package formfiller.delivery.controller;

import java.util.List;

import formfiller.appBoundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactoryImpl;

public abstract class AddFormComponentController implements Controller {
	private String questionId;
	private String questionContent;
	private String answerFormat;
	private Arguments arguments;
	
	public void handle(ParsedEvent parsedEvent) {
		assignRequiredParameters(parsedEvent.parameters);
		arguments = makeArguments();
		Request request = makeAddFormComponentRequest();
		UseCase useCase = makeAddFormComponentUseCase(request.name);
		
		useCase.execute(request);
	}
	
	protected void assignRequiredParameters(List<String> parameters) {
		questionId = assignInstanceVariable(parameters, 0);
		questionContent = assignInstanceVariable(parameters, 1);
		answerFormat = assignInstanceVariable(parameters, 2);
	}

	protected String assignInstanceVariable(List<String> values, int index) {
		if (index >= values.size()) return "";
		
		return values.get(index);
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("questionId", questionId);
		result.add("questionContent", questionContent);
		result.add("answerFormat", answerFormat);
		
		return result;
	}

	private Request makeAddFormComponentRequest(){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		String requestName = makeRequestName();
		Request result = requestBuilder.build(requestName, 
				arguments);
		return result;
	}
	
	protected abstract String makeRequestName();

	private UseCase makeAddFormComponentUseCase(String name) {
		UseCaseFactoryImpl factory = new UseCaseFactoryImpl();
		String useCaseName = name;
		return factory.make(useCaseName);
	}
}
