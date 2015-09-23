package formfiller.delivery.controller;

import java.util.HashMap;
import java.util.Map;

import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.entities.answerFormat.Unstructured;
import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.addFormComponent.AddUnstructuredFormComponentUseCase;
import formfiller.usecases.undoable.UndoableUseCase;

public class AddUnstructuredFormComponentController implements Controller {

	public void handle(ParsedEvent parsedEvent) {
		String questionId = parsedEvent.param;
		//	TODO:	All other params (after enabling multiple parameters.)
		Arguments arguments = makeArguments(makeArgumentsMap(questionId));
		Request request = makeAddUnstructuredFormComponentRequest(arguments);
		UndoableUseCase useCase = makeAddUnstructuredFormComponentUseCase();
		
		useCase.execute(request);
	}
	
	private HashMap<String, Object> makeArgumentsMap(String questionId) {
		HashMap<String, Object> result = new HashMap<String,Object>();
		result.put("questionId", questionId);
		result.put("questionContent", "questionContent");
		result.put("format", new Unstructured());
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

	protected Request makeAddUnstructuredFormComponentRequest(Arguments arguments){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("addUnstructuredFormComponent", 
				arguments);
		return result;
	}
	//	TODO:	Add to use case factory.
	private UndoableUseCase makeAddUnstructuredFormComponentUseCase() {
		AddUnstructuredFormComponentUseCase result = 
				new AddUnstructuredFormComponentUseCase();
		return result;
	}

}
