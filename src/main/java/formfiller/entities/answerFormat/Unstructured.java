package formfiller.entities.answerFormat;

public class Unstructured extends AnswerFormat {
	public Unstructured() {
		super();
	}
	
	public Unstructured(int minAnswers, int maxAnswers) {
		super(minAnswers, maxAnswers);
	}

	public boolean matchesFormat(Object content) {
		return content != null;
	}
}
