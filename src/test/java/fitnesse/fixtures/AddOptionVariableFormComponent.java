package fitnesse.fixtures;

import java.util.List;

import formfiller.entities.answerFormat.OptionVariable;

public class AddOptionVariableFormComponent extends AddFormComponent {
	private String options = "";

	public void givenAQuestionIdAndQuestionContentAndAnswerFormatAndOptions(String questionId, 
			String questionContent, String answerFormat, String options){
		super.givenAQuestionIdAndQuestionContentAndAnswerFormat(questionId, 
				questionContent, answerFormat);
		this.options = options;
	}
	
	protected String makeConsoleRequiredParametersString() {
		String result = super.makeConsoleRequiredParametersString() + 
				formattedOptions();
		return result;
	}
	
	private String formattedOptions() {
		return String.format("%s ", options);
	}

	protected String getCommandString() {
		return "AddFCOV";
	}

	public String addedOptions() {
		OptionVariable castFormat = (OptionVariable) addedComponent.format;
		String result = makeOptionsString(castFormat.options);
		return result;
	}

	private String makeOptionsString(List<Object> options) {
		String result = "";
		for (Object option : options)
			result += option.toString() + ", ";
		return trimLastCommaAndSpace(result);
	}

	private String trimLastCommaAndSpace(String input) {
		int lastComma = input.lastIndexOf(',');
		return input.substring(0, lastComma);
	}
}
