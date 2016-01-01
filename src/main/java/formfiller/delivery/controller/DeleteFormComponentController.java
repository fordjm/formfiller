package formfiller.delivery.controller;

import java.util.List;

/**
 * DeleteFormComponentController implements the abstract getName(), 
 * assignRequiredParameters(), and makeArguments() methods of the 
 * UndoableUseCaseController superclass.
 */
public class DeleteFormComponentController extends UndoableUseCaseController {
	private String componentId = "";
	
	protected String getName() {
		return "DeleteFormComponent";
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
