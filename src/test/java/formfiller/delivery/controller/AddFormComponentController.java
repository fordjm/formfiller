package formfiller.delivery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import formfiller.appBoundaries.UseCase;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactoryImpl;

public class AddFormComponentController implements Controller {
	private String questionId;
	private String questionContent;
	private String answerFormat;
	private Arguments arguments;
	
	public void handle(ParsedEvent parsedEvent) {
		assignInstanceVariables(parsedEvent.parameters);
		arguments = makeArguments(makeArgumentsMap());
		Request request = makeAddFormComponentRequest();
		UseCase useCase = makeAddFormComponentUseCase(request.name);
		
		useCase.execute(request);
	}
	
	private void assignInstanceVariables(List<String> parameters) {
		questionId = assignInstanceVariable(parameters, 0);
		questionContent = assignInstanceVariable(parameters, 1);
		answerFormat = assignInstanceVariable(parameters, 2);
	}

	private String assignInstanceVariable(List<String> parameters, int index) {
		if (index >= parameters.size()) return "";
		
		return parameters.get(index);
	}

	//	TODO:	Fix duplication in RequestBuilderImplTest
	private HashMap<String, Object> makeArgumentsMap() {
		HashMap<String, Object> result = new HashMap<String,Object>();
		result.put("questionId", questionId);
		result.put("questionContent", questionContent);
		result.put("answerFormat", answerFormat);
		return result;
	}
	
	private Arguments makeArguments(Map<String,Object> argumentsMap) {
		Arguments result = new Arguments();
		for (String key : argumentsMap.keySet()){
			Object value = argumentsMap.get(key);
			result.add(key, value);			
		}			
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
