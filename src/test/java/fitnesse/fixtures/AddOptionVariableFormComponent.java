package fitnesse.fixtures;

import java.util.List;

import formfiller.entities.answerFormat.OptionVariable;

public class AddOptionVariableFormComponent extends AddFormComponent {

	public void givenAQuestionIdAndQuestionContentAndAnswerFormatAndOptions(String questionId, 
			String questionContent, String answerFormat, String options){
		super.givenAQuestionIdAndQuestionContentAndAnswerFormat(questionId, 
				questionContent, answerFormat);
		this.options = options;
	}
	
	public String addedOptions() {
		OptionVariable castFormat = (OptionVariable) addedComponent.format;
		String result = makeOptionsString(castFormat.options);
		return result;
		//return "option1, option2";
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
