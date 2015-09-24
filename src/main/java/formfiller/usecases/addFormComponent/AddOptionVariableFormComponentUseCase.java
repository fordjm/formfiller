package formfiller.usecases.addFormComponent;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.OptionVariable;

public class AddOptionVariableFormComponentUseCase extends AddFormComponentUseCase {
	protected AnswerFormat makeAnswerFormat(int minAnswers, int maxAnswers) {
		return new OptionVariable(minAnswers, maxAnswers);
	}
}
