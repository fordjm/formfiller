package formfiller.entities.format;

import java.util.Collection;

import formfiller.entities.Answer;

public abstract class Format {
	protected String name;
	protected int minAnswers;
	protected int maxAnswers;
	
	public String getName() {
		return name;
	}
	
	public int getMinAnswers() {
		return minAnswers;
	}
	
	public int getMaxAnswers() {
		return maxAnswers;
	}
	
	public void setMinAnswers(int minAnswers) {
		if (minAnswers < 0) throw new IllegalArgumentException(
				makeIllegalAnswerCountMessage(minAnswers, "minimum"));
		else if (minAnswers > maxAnswers) throw new MaximumLessThanMinimum(
				makeMaximumLessThanMinimumMessage(minAnswers, maxAnswers));
		else
			this.minAnswers = minAnswers;
	}
	
	public abstract void setMaxAnswers(int maxAnswers);
	
	//	TODO:	canAcceptAnswer(Answer) = matchesCardinality() && matchesFormat()
	//			Where is this functionality now?
	//			Swap "matches" with "accepts?"
	
	public abstract boolean matchesCardinality(Answer toMatch);
	
	public abstract boolean matchesFormat(Object content);

	protected boolean isNotALegalSingleAnswer(Answer toMatch) {
		return toMatch == null || isAnswerContentACollection(toMatch.getContent());
	}
	
	protected boolean isAnswerContentACollection(Object content) {
		return content instanceof Collection;
	}

	protected String makeIllegalAnswerCountMessage(int count, String boundary) {
		String result = count + " is not a legal " + boundary + 
				" for format " + name.toLowerCase() + ".";
		return result;
	}

	protected String makeMaximumLessThanMinimumMessage(int minAnswers, 
			int maxAnswers) {
		String result = "Minimum " + minAnswers + " is greater than maximum " + 
			maxAnswers + ".";
		return result;
	}

	public class MaximumLessThanMinimum extends RuntimeException {

		public MaximumLessThanMinimum(String message) {
			super(message);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
}