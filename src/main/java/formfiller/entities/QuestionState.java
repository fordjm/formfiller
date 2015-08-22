package formfiller.entities;

import formfiller.ApplicationContext;

public class QuestionState {
	private int questionIndex;
	
	public QuestionState(int questionIndex){
		this.questionIndex = questionIndex;
				
	}
	
	public Prompt getQuestion() {
		return ApplicationContext.questionGateway.findQuestionByIndex(questionIndex);
	}
	public Prompt findQuestionByIndexOffset(int offset){
		if (offset == 0) return getQuestion();
		int requestedIndex = computeRequestedIndex(offset);
		updateCurrentIndex(requestedIndex);
		return ApplicationContext.questionGateway.findQuestionByIndex(requestedIndex);
	}

	private int computeRequestedIndex(int offset) {
		return questionIndex + offset;
	}	
	void updateCurrentIndex(int requestedIndex){
		if (isAtStart(requestedIndex)) 
			questionIndex = -1;
		else if (isAtEnd(requestedIndex)) 
			questionIndex = ApplicationContext.questionGateway.numQuestions();
		else
			questionIndex = requestedIndex;
	}
	boolean isAtStart(int index) {
		return index < 0;
	}
	boolean isAtEnd(int index) {
		return index >= ApplicationContext.questionGateway.numQuestions();
	}

}
