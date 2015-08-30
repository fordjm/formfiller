package formfiller.boundaryCrossers;

public class PresentableFormComponent extends PresentableResponseImpl {
	public PresentableQuestion question;
	public PresentableAnswer answer;

	public void setQuestion(PresentableQuestion question) {
		this.question = question;
	}

	public void setAnswer(PresentableAnswer answer) {
		this.answer = answer;
	}
}
