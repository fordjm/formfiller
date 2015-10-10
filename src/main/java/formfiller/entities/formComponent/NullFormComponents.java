package formfiller.entities.formComponent;

import formfiller.entities.AnswerImpl;
import formfiller.entities.Question;
import formfiller.entities.QuestionImpl;
import formfiller.entities.format.Unstructured;

//	TODO:	Make this an enum.
//			Can this class extend FormComponent but have immutable fields?
//			(Probably not.  Could make fields private and implement empty setter fct.)
public class NullFormComponents {
	public static final FormComponent START = makeNullFormComponent(QuestionImpl.START);
	public static final FormComponent END = makeNullFormComponent(QuestionImpl.END);
	public static final FormComponent NULL = makeNullFormComponent(QuestionImpl.NULL);
	
	private static FormComponent makeNullFormComponent(Question nullQuestion) {
		FormComponent result = new FormComponent();
		result.id = nullQuestion.getId();
		result.question = nullQuestion;
		result.answer = AnswerImpl.NONE;
		result.format = new Unstructured();
		return result;
	}
	
}
