package formfiller.entities;

import formfiller.ApplicationContext;

public class AnswerState {
	int answerIndex;

	public AnswerState(int answerIndex) {
		this.answerIndex = answerIndex;				
	}
	
	public Answer getAnswer() {
		return ApplicationContext.answerGateway.findAnswerByIndex(answerIndex);
	}
	public Answer findAnswerByIndexOffset(int offset){
		int requestedIndex = answerIndex  + offset;
		updateCurrentIndex(requestedIndex);
		return ApplicationContext.answerGateway.findAnswerByIndex(requestedIndex);
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
