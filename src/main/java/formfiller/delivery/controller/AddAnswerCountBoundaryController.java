package formfiller.delivery.controller;

import java.util.List;

/**
 * AddAnswerCountBoundaryController implements the abstract getName(), 
 * assignRequiredParameters(), and makeArguments() methods of the 
 * UndoableUseCaseController superclass.
 */
public class AddAnswerCountBoundaryController extends UndoableUseCaseController {
	private String componentId = "";
	private String boundary = "";
	private int count;

	protected String getName() {
		return "AddAnswerCountBoundary";
	}

	protected void assignRequiredParameters(List<String> parameters) {
		componentId = assignRequiredParameter(parameters, 0);
		boundary = assignRequiredParameter(parameters, 1);
		count = Integer.parseInt(assignRequiredParameter(parameters, 2));
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("componentId", componentId);
		result.add("boundary", boundary);
		result.add("count", count);
		return result;
	}

}
