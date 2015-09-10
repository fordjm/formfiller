package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.entities.Answer;
import formfiller.entities.Question;
import formfiller.entities.formComponent.FormComponent;

public class FormComponentMocker {

	public static FormComponent makeMockFormComponent(String id) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = id;
		return result;
	}

	public static FormComponent makeMockFormComponent(boolean requiresAnswer, 
			Question question, Answer answer) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = question.id;
		result.requiresAnswer = requiresAnswer;
		result.question = question;
		result.answer = answer;
		return result;
	}
}
