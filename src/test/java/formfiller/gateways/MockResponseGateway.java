package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.NullAnswer;
import formfiller.entities.Answer;

public class MockResponseGateway implements ResponseGateway {
	private Answer currentResponse = (Answer) new NullAnswer();
	private int currentIndex = -1;
	List<Answer> responses;
	
	public MockResponseGateway(){
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
