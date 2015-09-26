package formfiller.delivery.controller;

import java.util.List;

import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactory;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.usecases.undoable.UndoableUseCase;

public class DeleteFormComponentController extends UndoableUseCaseController {
	private String componentId = "";
	private String name = "DeleteFormComponent";

	protected void assignRequiredParameters(List<String> parameters) {
		componentId = assignRequiredParameter(parameters, 0);
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("componentId", componentId);
		return result;
	}

	protected Request makeRequest(Arguments arguments) {
		RequestBuilderImpl builder = new RequestBuilderImpl();
		return builder.build(name, arguments);
	}

	protected UndoableUseCase makeUseCase() {
		UseCaseFactory factory = new UseCaseFactoryImpl();
		UndoableUseCase castUseCase = (UndoableUseCase) factory.make(name);
		return castUseCase;
	}
}
