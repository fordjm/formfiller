package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.NoAnswer;
import formfiller.entities.Answer;

public class MockAnswerGateway implements AnswerGateway {
	private Answer currentResponse = (Answer) new NoAnswer();
	private int currentIndex = -1;
	List<Answer> responses;
	
	public MockAnswerGateway(){
		responses = new ArrayList<Answer>();
	}
	
	public Answer findResponseByIndexOffset(int offset){
		int requestedIndex = currentIndex  + offset;
		updateCurrentResponse(requestedIndex);
		return currentResponse;
	}
	void updateCurrentResponse(int requestedIndex) {
		updateCurrentIndex(requestedIndex);
		currentResponse = responses.get(currentIndex);
	}
	void updateCurrentIndex(int requestedIndex){
		if (isLegalIndex(requestedIndex))
			currentIndex = requestedIndex;
	}
	boolean isLegalIndex(int requestedIndex) {
		return requestedIndex >= 0 && requestedIndex < responses.size();
	}
	public Answer getResponse() {
		return currentResponse;
	}

	public void save(Answer response) {
		responses.add(response);
	}
}
