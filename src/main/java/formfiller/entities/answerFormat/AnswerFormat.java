package formfiller.entities.answerFormat;

import java.util.Collection;

import formfiller.entities.Answer;

public abstract class AnswerFormat {
	protected String name = "";
	int minAnswers;
	int maxAnswers;
	
	public AnswerFormat() {
		minAnswers = 0;
		maxAnswers = 1;
	}
	
	public String getName() {
		return name;
	}
	
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
		return toMatch == null || answerContentIsACollection(toMatch.content);
	}

	private boolean answerContentIsACollection(Object content) {
		return content instanceof Collection;
	}
	
	private boolean multipleAnswerMatchesCardinality(Answer toMatch) {
		if (isNotALegalMultipleAnswer(toMatch)) return false;
		
		Collection<Object> castContent = (Collection<Object>) toMatch.content;
		return castContent.size() >= minAnswers && 
				castContent.size() <= maxAnswers;
	}

	private boolean isNotALegalMultipleAnswer(Answer toMatch) {
		return toMatch == null || !answerContentIsACollection(toMatch.content);
	}
	
	//	TODO:	Must treat single and multiple answers differently.
	public abstract boolean matchesFormat(Object content);
}