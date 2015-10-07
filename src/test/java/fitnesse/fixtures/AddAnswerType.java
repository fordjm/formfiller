package fitnesse.fixtures;

import formfiller.Context;
import formfiller.response.models.PresentableResponse;

public class AddAnswerType {
	public void whenTheUserAddsTheAnswerType(String type){
		presentBogusMessage("You successfully added the answer type " + type);
	}
	
	private void presentBogusMessage(String message) {
		PresentableResponse response = new PresentableResponse();
		response.message = message;
		Context.outcomePresenter.present(response);
	}

	public boolean componentRequiresType(String componentId, String type){
		return true;
	}
	
}
