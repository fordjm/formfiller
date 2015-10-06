package formfiller.delivery.controller;

import java.util.List;

public abstract class AddFormComponentController extends UndoableUseCaseController {
	private String questionId;
	private String questionContent;
	private String answerFormat;

	protected void assignRequiredParameters(List<String> parameters) {
		questionId = assignRequiredParameter(parameters, 0);
		questionContent = assignRequiredParameter(parameters, 1);
		answerFormat = assignRequiredParameter(parameters, 2);
	}

	protected Arguments makeArguments() {
		Arguments result = new Arguments();
		result.add("componentId", questionId);
		result.add("questionContent", questionContent);
		result.add("format", answerFormat);
		return result;
	}
}
