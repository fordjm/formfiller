package formfiller.entities.format;

import java.util.Collection;

import formfiller.entities.Answer;

public class MultiOptionVariable extends OptionVariable {	
	public MultiOptionVariable() {
		super();
		minAnswers = 0;
		maxAnswers = 2;
		name = "MultiOptionVariable";
	}

	public void setMaxAnswers(int maxAnswers) {
		if (maxAnswers == 0 || maxAnswers == 1) throw new IllegalStateException(
				makeIllegalAnswerCountMessage(maxAnswers, "maximum"));
		else if (minAnswers > maxAnswers) throw new MaximumLessThanMinimum(
					makeMaximumLessThanMinimumMessage(minAnswers, maxAnswers));
		else
			this.maxAnswers = maxAnswers;
	}
	
	public boolean matchesCardinality(Answer toMatch) {
		if (isNotALegalMultipleAnswer(toMatch)) return false;
		
		Collection<Object> castContent = (Collection<Object>) toMatch.getContent();
		return castContent.size() >= minAnswers && 
				castContent.size() <= maxAnswers;
	}

	private boolean isNotALegalMultipleAnswer(Answer toMatch) {
		return toMatch == null || !isAnswerContentACollection(toMatch.getContent());
	}

}
