package formfiller.entities.formComponent;

import formfiller.entities.Answer;
import formfiller.entities.NullQuestions;
import formfiller.entities.Question;

public class NullFormComponents {
	public static final FormComponent START = makeNullFormComponent(NullQuestions.START);
	public static final FormComponent END = makeNullFormComponent(NullQuestions.END);
	public static final FormComponent NULL = makeNullFormComponent(NullQuestions.NULL);
	
	private NullFormComponents() { }
	
	private static FormComponent makeNullFormComponent(Question nullQuestion) {
		FormComponent result = new FormComponent();
		result.id = nullQuestion.id;
		result.question = nullQuestion;
		result.answer = Answer.NONE;
		return result;
	}
}
