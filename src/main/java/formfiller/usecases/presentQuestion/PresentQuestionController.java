package formfiller.usecases.presentQuestion;

import java.util.HashMap;

import formfiller.Controller;
import formfiller.ui.userRequestParser.ParsedUserRequest;
import formfiller.usecases.Request;
import formfiller.usecases.RequestBuilder;
import formfiller.usecases.RequestBuilderImpl;

public class PresentQuestionController implements Controller {

	public void handle(ParsedUserRequest parsedInput) {
		Request presentQuestionRequest = 
				makePresentQuestionRequest();
		PresentQuestionUseCase presentQuestionUseCase = 
				new PresentQuestionUseCase();
		presentQuestionUseCase.execute(presentQuestionRequest);
	}
	
	public Request makePresentQuestionRequest(){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		Request result = requestBuilder.build("presentQuestion", new HashMap());
		return result;
	}

}
