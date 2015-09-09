package formfiller.entities.formComponent;

import formfiller.entities.Answer;
import formfiller.entities.NoQuestion;

public class NullFormComponents {
	public static final FormComponent START = makeNullFormComponent(NoQuestion.START);
	public static final FormComponent END = makeNullFormComponent(NoQuestion.END);
	public static final FormComponent NULL = makeNullFormComponent(NoQuestion.NULL);
	
	
	private static FormComponent makeNullFormComponent(NoQuestion noQuestion) {
		FormComponent result = new FormComponent();
		result.id = noQuestion.getId();
		result.question = noQuestion;
		result.answer = Answer.NONE;
		return result;
	}
}
