package formfiller.entities;

public class FormComponent {
	public String id = "";
	public Prompt question;
	public Answer answer;
	
	public static final FormComponent START = makeIllegalIndexFormComponent(Question.START);
	public static final FormComponent END = makeIllegalIndexFormComponent(Question.END);
		
	private static FormComponent makeIllegalIndexFormComponent(NoQuestion noQuestion) {
		FormComponent result = new FormComponent();
		result.id = noQuestion.getId();
		result.question = noQuestion;
		result.answer = AnswerImpl.NONE;
		return result;
	}
}
