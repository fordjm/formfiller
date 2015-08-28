package formfiller.entities;

public class FormComponent {
	public String id = "";
	Prompt question;
	Answer answer;

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
