package formfiller.entities.format;

//	TODO:	Unstructured and SOV extend OptionVariableWithAtMostOneAnswer
public class SingleOptionVariable extends OptionVariable {	
	public SingleOptionVariable() {
		super();
		minAnswers = 0;
		maxAnswers = 1;
		name = "SingleOptionVariable";
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
