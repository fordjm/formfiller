package formfiller.entities.format;

public class MultiOptionVariable extends OptionVariable {	
	public MultiOptionVariable() {
		super();
		minAnswers = 0;
		maxAnswers = 2;
		name = "MultiOptionVariable";
	}

	public void setMinAnswers(int minAnswers) {
		if (minAnswers < 0) throw new IllegalArgumentException();
		else if (minAnswers > maxAnswers) throw new MaximumLessThanMinimum(
					makeMaximumLessThanMinimumMessage(minAnswers, maxAnswers));
		else
			this.minAnswers = minAnswers;
	}

	public void setMaxAnswers(int maxAnswers) {
		if (maxAnswers == 0 || maxAnswers == 1) throw new IllegalStateException(
				makeIllegalAnswerCountMessage(maxAnswers, "maximum"));
		else if (minAnswers > maxAnswers) throw new MaximumLessThanMinimum(
					makeMaximumLessThanMinimumMessage(minAnswers, maxAnswers));
		else
			this.maxAnswers = maxAnswers;
	}

}
