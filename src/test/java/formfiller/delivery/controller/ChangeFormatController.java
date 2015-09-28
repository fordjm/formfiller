package formfiller.delivery.controller;

import java.util.List;

public abstract class ChangeFormatController extends UndoableUseCaseController {

	private String componentId;

	public ChangeFormatController() {
		super();
	}

	protected void assignRequiredParameters(List<String> parameters) {
		componentId = assignRequiredParameter(parameters, 0);
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("componentId", componentId);
		return result;
	}

}