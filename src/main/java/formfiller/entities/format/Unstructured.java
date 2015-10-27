package formfiller.entities.format;

public class Unstructured extends Format {
	public Unstructured() {
		super();
		minAnswers = 0;
		maxAnswers = 1;
		name = "Unstructured";
	}

	public boolean matchesFormat(Object content) {
		return content != null;
	}

	public void setMinAnswers(int minAnswers) {
		if (minAnswers > 1) throw new IllegalArgumentException(
				makeIllegalAnswerCountMessage(minAnswers, "minimum"));
		this.minAnswers = minAnswers;
	}

	public void setMaxAnswers(int maxAnswers) {
		throw new IllegalStateException(
				"Setting maximum is not a legal operation for format "
						+ name.toLowerCase() + ".");
	}
	
}
