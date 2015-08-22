package formfiller.entities;

import formfiller.ApplicationContext;

public class FormState {
	private int questionIndex;
	private Prompt currentQuestion;
	
	public FormState(int questionIndex){
		this.questionIndex = questionIndex;
		setCurrentQuestionToStartPrompt();
	}

	private void setCurrentQuestionToStartPrompt() {
		currentQuestion = new StartPrompt();
	}
	
	public Prompt getQuestion() {
		return currentQuestion;
	}
	public Prompt findQuestionByIndexOffset(int offset){
		if (offset == 0) return currentQuestion;
		int requestedIndex = questionIndex + offset;
		updateCurrentIndex(requestedIndex);
		updateCurrentQuestion(requestedIndex);
		return currentQuestion;
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
			currentQuestion = new StartPrompt();
		else if (isAtEnd(requestedIndex))
			currentQuestion = new EndPrompt();
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
