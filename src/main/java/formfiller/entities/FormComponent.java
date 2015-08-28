package formfiller.entities;

public class FormComponent {
	public String id = "";
	Prompt question;
	Answer answer;
	
	public static final FormComponent START = makeIllegalIndexFormComponent(Question.START);
	public static final FormComponent END = makeIllegalIndexFormComponent(Question.END);
		
	private static FormComponent makeIllegalIndexFormComponent(NoQuestion noQuestion) {
		FormComponent result = new FormComponent();
		result.setQuestion(noQuestion);
		result.setAnswer(AnswerImpl.NONE);
		return result;
	}

	public void setQuestion(Prompt question) {
		if (question == null) 
			this.question = new NoQuestion();
		else
			this.question = question;		
		setId();
	}

	private void setId() {
		id = question.getId();
	}

	public void setAnswer(Answer answer) {
		if (answer == null) 
			this.answer = AnswerImpl.NONE;
		else
			this.answer = answer;
	}
}
