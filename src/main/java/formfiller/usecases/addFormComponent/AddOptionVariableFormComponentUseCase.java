package formfiller.usecases.addFormComponent;

import java.util.List;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.OptionVariable;
import formfiller.request.models.AddOptionVariableFormComponentRequest;

public class AddOptionVariableFormComponentUseCase extends AddFormComponentUseCase {	
	protected AnswerFormat makeAnswerFormat(int minAnswers, int maxAnswers) {
		OptionVariable result = new OptionVariable(minAnswers, maxAnswers);
		result.options = getOptions();
		return result;
	}

	private List<Object> getOptions() {
		AddOptionVariableFormComponentRequest recastRequest = 
				(AddOptionVariableFormComponentRequest) castRequest;
		OptionVariable castFormat = (OptionVariable) recastRequest.format;
		return castFormat.options;
	}
}
