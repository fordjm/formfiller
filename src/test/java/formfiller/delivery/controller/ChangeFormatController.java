package formfiller.delivery.controller;

import java.util.List;

import formfiller.entities.format.Format;
import formfiller.utilities.FormatArgumentParser;

public class ChangeFormatController extends UndoableUseCaseController {
	private String componentId;
	private String formatString;
	private Format format;

	protected String getName() {
		return "ChangeFormat";
	}

	protected void assignRequiredParameters(List<String> parameters) {
		componentId = assignRequiredParameter(parameters, 0);
		formatString = assignRequiredParameter(parameters, 1);
		format = FormatArgumentParser.parseFormat(formatString);
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("componentId", componentId);
		result.add("format", format);
		return result;
	}

}