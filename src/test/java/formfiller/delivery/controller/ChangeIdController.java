package formfiller.delivery.controller;

import java.util.List;

import formfiller.request.builders.RequestBuilderImpl;
import formfiller.request.models.Request;
import formfiller.usecases.factory.UseCaseFactory;
import formfiller.usecases.factory.UseCaseFactoryImpl;
import formfiller.usecases.undoable.UndoableUseCase;

public class ChangeIdController extends UndoableUseCaseController {
	private String name = "ChangeId";
	private String oldId = "";
	private String newId = "";

	protected void assignRequiredParameters(List<String> parameters) {
		this.oldId = assignRequiredParameter(parameters, 0);
		this.newId = assignRequiredParameter(parameters, 1);
	}
	
	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("oldId", oldId);
		result.add("newId", newId);
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
