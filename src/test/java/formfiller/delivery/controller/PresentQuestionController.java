package formfiller.delivery.controller;

import java.util.HashMap;

import formfiller.delivery.Controller;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.request.Request;
import formfiller.request.RequestBuilder;
import formfiller.request.RequestBuilderImpl;
import formfiller.usecases.presentQuestion.PresentQuestionUseCase;

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
