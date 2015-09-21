package formfiller.entities.answerFormat;

public class UnstructuredAnswerFormat extends AnswerFormat {
	public UnstructuredAnswerFormat() {
		super();
	}
	
	public UnstructuredAnswerFormat(int minAnswers, int maxAnswers) {
		super(minAnswers, maxAnswers);
	}

	public boolean matchesFormat(Object content) {
		return content != null;
	}
}
