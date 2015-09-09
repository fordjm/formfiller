package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.entities.Answer;
import formfiller.entities.Prompt;
import formfiller.entities.formComponent.FormComponent;

public class FormComponentMocker {

	public static FormComponent makeMockFormComponent(String id) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = id;
		return result;
	}

	public static FormComponent makeMockFormComponent(Prompt question, Answer answer) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = question.getId();
		result.question = question;
		result.answer = answer;
		return result;
	}
}
