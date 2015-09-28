package formfiller.usecases.addFormComponent;

import formfiller.entities.answerFormat.AnswerFormat;
import formfiller.entities.answerFormat.Unstructured;

public class AddUnstructuredFormComponentUseCase extends AddFormComponentUseCase {
	protected AnswerFormat makeAnswerFormat() {
		return new Unstructured();
	}
	
}
