package formfiller.delivery.controller;

import java.util.List;

import formfiller.request.builders.RequestBuilder;
import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.usecases.undoable.UndoableUseCase;

public abstract class AddFormComponentController extends UndoableUseCaseController {
	private String questionId;
	private String questionContent;
	private String answerFormat;
	private String name;

	protected void assignRequiredParameters(List<String> parameters) {
		questionId = assignRequiredParameter(parameters, 0);
		questionContent = assignRequiredParameter(parameters, 1);
		answerFormat = assignRequiredParameter(parameters, 2);
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("questionId", questionId);
		result.add("questionContent", questionContent);
		result.add("answerFormat", answerFormat);
		return result;
	}

	protected Request makeRequest(Arguments arguments){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		name = makeRequestName();
		Request result = requestBuilder.build(name, arguments);
		return result;
	}
	
	protected abstract String makeRequestName();

	protected UndoableUseCase makeUseCase() {
		UseCaseFactoryImpl factory = new UseCaseFactoryImpl();
		UndoableUseCase castUseCase = (UndoableUseCase) factory.make(name);
		return castUseCase;
	}
}
