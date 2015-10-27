package formfiller.entities.format;

import java.util.Collection;

import formfiller.entities.Answer;

public abstract class Format {
	//	TODO:	Decide whether these are public or private and be consistent.
	protected String name = "";
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
	
	public abstract void setMinAnswers(int minAnswers);
	
	public abstract void setMaxAnswers(int maxAnswers);
	
	//	TODO:	canAcceptAnswer(Answer) = matchesCardinality() && matchesFormat()
	
	public boolean matchesCardinality(Answer toMatch) {
		return singleAnswerMatchesCardinality(toMatch) || 
				multipleAnswerMatchesCardinality(toMatch);
	}

	private boolean singleAnswerMatchesCardinality(Answer toMatch) {
		if (isNotALegalSingleAnswer(toMatch)) 
			return false;
		
		return maxAnswers > 0 && minAnswers < 2;
	}

	private boolean isNotALegalSingleAnswer(Answer toMatch) {
		return toMatch == null || answerContentIsACollection(toMatch.getContent());
	}

	private boolean answerContentIsACollection(Object content) {
		return content instanceof Collection;
	}
	
	private boolean multipleAnswerMatchesCardinality(Answer toMatch) {
		if (isNotALegalMultipleAnswer(toMatch)) return false;
		
		Collection<Object> castContent = (Collection<Object>) toMatch.getContent();
		return castContent.size() >= minAnswers && 
				castContent.size() <= maxAnswers;
	}

	//	TODO:	Fix this.  MultiOptionVariable can accept 0 or 1 answers.
	private boolean isNotALegalMultipleAnswer(Answer toMatch) {
		return toMatch == null || !answerContentIsACollection(toMatch.getContent());
	}
	
	//	TODO:	Must treat single and multiple answers differently.
	public abstract boolean matchesFormat(Object content);

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