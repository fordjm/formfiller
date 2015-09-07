package formfiller.entities;

public class FormComponent {	
	public static final FormComponent START = makeNullFormComponent(NoQuestion.START);
	public static final FormComponent END = makeNullFormComponent(NoQuestion.END);
	public static final FormComponent NULL = makeNullFormComponent(NoQuestion.NULL);
	
	public String id = "";
	public Prompt question;
	public ConstrainableAnswer answer;
		
	private static FormComponent makeNullFormComponent(NoQuestion noQuestion) {
		FormComponent result = new FormComponent();
		result.id = noQuestion.getId();
		result.question = noQuestion;
		result.answer = ConstrainableAnswer.NONE;
		return result;
	}
}
