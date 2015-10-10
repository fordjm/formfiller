package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.entities.Answer;
import formfiller.entities.QuestionImpl;
import formfiller.entities.formComponent.FormComponent;
import formfiller.entities.format.Format;

public class FormComponentMocker {

	public static FormComponent makeMockFormComponent(String id) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = id;
		return result;
	}

	public static FormComponent makeMockFormComponent(boolean requiresAnswer, 
			QuestionImpl question, Answer answer, Format format) {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = question.getId();
		result.requiresAnswer = requiresAnswer;
		result.question = question;
		result.answer = answer;
		result.format = format;
		return result;
	}
}
