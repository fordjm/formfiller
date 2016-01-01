package formfiller.delivery.controller;

import java.util.List;

/**
 * ChangeIdController implements the abstract getName(), 
 * assignRequiredParameters(), and makeArguments() methods of the 
 * UndoableUseCaseController superclass.
 */
public class ChangeIdController extends UndoableUseCaseController {
	private String oldId = "";
	private String newId = "";
	
	protected String getName() {
		return "ChangeId";
	}

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
}
