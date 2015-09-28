package formfiller.entities.answerFormat;

public class Unstructured extends AnswerFormat {
	public Unstructured() {
		super();
		name = "Unstructured";
	}

	public boolean matchesFormat(Object content) {
		return content != null;
	}
	
}
