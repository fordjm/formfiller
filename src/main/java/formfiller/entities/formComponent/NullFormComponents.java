package formfiller.entities.formComponent;

import formfiller.entities.Answer;
import formfiller.entities.NullQuestions;
import formfiller.entities.Question;
import formfiller.entities.answerFormat.Unstructured;

//	TODO:	Make this an enum.
//			Can this class extend FormComponent but have immutable fields?
//			(Probably not.  Could make fields private and implement empty setter fct.)
public class NullFormComponents {
	public static final FormComponent START = makeNullFormComponent(NullQuestions.START);
	public static final FormComponent END = makeNullFormComponent(NullQuestions.END);
	public static final FormComponent NULL = makeNullFormComponent(NullQuestions.NULL);
	
	private static FormComponent makeNullFormComponent(Question nullQuestion) {
		FormComponent result = new FormComponent();
		result.id = nullQuestion.id;
		result.question = nullQuestion;
		result.answer = Answer.NONE;
		result.format = new Unstructured();
		return result;
	}
}
