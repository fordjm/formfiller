package formfiller.entities;

import formfiller.ApplicationContext;

public class AnswerState {
	int answerIndex;
	Answer currentAnswer;

	public AnswerState(int answerIndex) {
		this.answerIndex = answerIndex;
		this.currentAnswer = 
				ApplicationContext.answerGateway.findAnswerByIndex(answerIndex);
	}
	
	public Answer getAnswer() {
		return currentAnswer;
	}
	public Answer findAnswerByIndexOffset(int offset){
		int requestedIndex = answerIndex  + offset;
		updateCurrentIndex(requestedIndex);
		updateCurrentResponse(requestedIndex);
		return currentAnswer;
	}
	void updateCurrentResponse(int requestedIndex) {
		currentAnswer = 
				ApplicationContext.answerGateway.findAnswerByIndex(requestedIndex);
	}
	void updateCurrentIndex(int requestedIndex){
		if (isLegalIndex(requestedIndex))
			answerIndex = requestedIndex;
	}
	boolean isLegalIndex(int requestedIndex) {
		return requestedIndex >= 0 && 
				requestedIndex < ApplicationContext.answerGateway.numResponses();
	}

}
