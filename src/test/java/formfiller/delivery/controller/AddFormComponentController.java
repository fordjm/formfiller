package formfiller.delivery.controller;

import java.util.List;

import formfiller.appBoundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactoryImpl;

public class AddFormComponentController implements Controller {
	String questionId;
	String questionContent;
	String answerFormat;
	
	//	JUNK
	String options;

	Arguments arguments;
	
	public void handle(ParsedEvent parsedEvent) {
		assignInstanceVariables(parsedEvent.parameters);
		arguments = makeArguments();
		Request request = makeAddFormComponentRequest();
		UseCase useCase = makeAddFormComponentUseCase(request.name);
		
		useCase.execute(request);
	}
	
	private void assignInstanceVariables(List<String> parameters) {
		questionId = assignInstanceVariable(parameters, 0);
		questionContent = assignInstanceVariable(parameters, 1);
		answerFormat = assignInstanceVariable(parameters, 2);
		
		//	JUNK
		if (isOptionVariable())
			options = assignInstanceVariable(parameters, 3);
	}

	private boolean isOptionVariable() {
		return answerFormat.equals("V");
	}

	private String assignInstanceVariable(List<String> parameters, int index) {
		if (index >= parameters.size()) return "";
		
		return parameters.get(index);
	}
	
	private Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("questionId", questionId);
		result.add("questionContent", questionContent);
		result.add("answerFormat", answerFormat);
		
		//		JUNK
		if (isOptionVariable())
			result.add("options", options);
		
		return result;
	}

	protected Request makeAddFormComponentRequest(){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("addFormComponent", 
				arguments);
		return result;
	}

	private UseCase makeAddFormComponentUseCase(String name) {
		UseCaseFactoryImpl factory = new UseCaseFactoryImpl();
		String useCaseName = name;
		return factory.make(useCaseName);
	}
}
