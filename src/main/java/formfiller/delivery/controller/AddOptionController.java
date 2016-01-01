package formfiller.delivery.controller;

import java.util.List;

/**
 * AddOptionController implements the abstract getName(), 
 * assignRequiredParameters(), and makeArguments() methods of the 
 * UndoableUseCaseController superclass.
 */
public class AddOptionController extends UndoableUseCaseController {
	private String componentId = "";
	private String option = "";

	protected String getName() {
		return "AddOption";
	}

	protected void assignRequiredParameters(List<String> parameters) {
		componentId = assignRequiredParameter(parameters, 0);
		option = assignRequiredParameter(parameters, 1);
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("componentId", componentId);
		result.add("option", option);
		return result;
	}

}
