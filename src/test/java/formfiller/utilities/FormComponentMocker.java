package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.entities.ConstrainableAnswer;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;

public class FormComponentMocker {

	public static FormComponent makeMockFormComponent(String id) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = id;
		return result;
	}

	public static FormComponent makeMockFormComponent(Prompt question, ConstrainableAnswer answer) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = question.getId();
		result.question = question;
		result.answer = answer;
		return result;
	}
}
