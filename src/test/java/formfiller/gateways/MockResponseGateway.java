package formfiller.gateways;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.NullResponse;
import formfiller.entities.Response;

public class MockResponseGateway implements ResponseGateway {
	private Response currentResponse = (Response) new NullResponse();
	private int currentIndex = -1;
	List<Response> responses;
	
	public MockResponseGateway(){
		responses = new ArrayList<Response>();
	}
	
	public Response findResponseByIndexOffset(int offset){
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
	public Response getResponse() {
		return currentResponse;
	}

	public void save(Response response) {
		responses.add(response);
	}
}
