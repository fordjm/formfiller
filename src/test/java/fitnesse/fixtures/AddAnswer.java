package fitnesse.fixtures;

import formfiller.Context;

public class AddAnswer {
	private boolean hasExistingAnswer;
	private String answerContent;
	
	public void whenTheUserAddsTheAnswer(String answerContent){
		if (hasExistingAnswer)
			this.answerContent = "existing answer";
		else
			this.answerContent = answerContent;
	}
	
	public boolean doesComponentHaveAnswer(String id, String answerContent){
		return Context.stringMatcher.matches(this.answerContent, answerContent);
	}
	
	public void givenComponentHasAnswers(String id, int numAnswers){
		hasExistingAnswer = numAnswers > 0;
	}
	
}
