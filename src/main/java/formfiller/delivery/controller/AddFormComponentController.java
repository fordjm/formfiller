package formfiller.delivery.controller;

import java.util.List;

import formfiller.entities.format.Format;
import formfiller.utilities.FormatArgumentParser;

/**
 * AddFormComponentController implements the abstract getName(), 
 * assignRequiredParameters(), and makeArguments() methods of the 
 * UndoableUseCaseController superclass.
 */
public class AddFormComponentController extends UndoableUseCaseController {
	private String componentId;
	private String questionContent;
	private String formatString;
	private Format format;
	
	protected String getName() {
		return "AddFormComponent";
	}

	protected void assignRequiredParameters(List<String> parameters) {
		componentId = assignRequiredParameter(parameters, 0);
		questionContent = assignRequiredParameter(parameters, 1);
		formatString = assignRequiredParameter(parameters, 2);
		format = FormatArgumentParser.parseFormat(formatString);
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("componentId", componentId);
		result.add("questionContent", questionContent);
		result.add("format", format);
		return result;
	}
	
}
