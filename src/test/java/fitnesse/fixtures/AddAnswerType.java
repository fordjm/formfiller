package fitnesse.fixtures;

import formfiller.Context;
import formfiller.response.models.PresentableResponse;

//	TODO:	Consider String-to-type parser at https://github.com/drapostolos/type-parser
public class AddAnswerType {
	public void whenTheUserAddsTheAnswerType(String type){
		//	TODO:	Determine what type it is.
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
