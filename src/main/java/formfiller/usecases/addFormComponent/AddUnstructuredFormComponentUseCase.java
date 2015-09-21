package formfiller.usecases.addFormComponent;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.UnstructuredAnswerFormat;

public class AddUnstructuredFormComponentUseCase extends AddFormComponentUseCase {
	protected AnswerFormat makeAnswerFormat(int minAnswers, int maxAnswers) {
		return new UnstructuredAnswerFormat(minAnswers, maxAnswers);
	}
}
