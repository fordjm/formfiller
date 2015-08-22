package formfiller.entities;

import formfiller.ApplicationContext;

public class QuestionState {
	private int questionIndex;
	private Prompt currentQuestion;
	
	public QuestionState(int questionIndex){
		this.questionIndex = questionIndex;
		currentQuestion = 
				ApplicationContext.questionGateway.findQuestionByIndex(questionIndex);
	}
	
	public Prompt getQuestion() {
		return currentQuestion;
	}
	public Prompt findQuestionByIndexOffset(int offset){
		if (offset == 0) return currentQuestion;
		int requestedIndex = computeRequestedIndex(offset);
		updateCurrentIndex(requestedIndex);
		updateCurrentQuestion(requestedIndex);
		return currentQuestion;
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
	void updateCurrentQuestion(int requestedIndex) {
		if (isAtStart(requestedIndex)) 
			currentQuestion = Question.START;
		else if (isAtEnd(requestedIndex))
			currentQuestion = Question.END;
		else
			currentQuestion = ApplicationContext.questionGateway.findQuestionByIndex(requestedIndex);
	}
	boolean isAtStart(int index) {
		return index < 0;
	}
	boolean isAtEnd(int index) {
		return index >= ApplicationContext.questionGateway.numQuestions();
	}

}
