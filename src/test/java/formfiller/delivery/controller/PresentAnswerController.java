package formfiller.delivery.controller;

import java.util.HashMap;

import formfiller.delivery.Controller;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.request.builder.RequestBuilder;
import formfiller.request.builder.RequestBuilderImpl;
import formfiller.request.interfaces.Request;
import formfiller.usecases.presentAnswer.PresentAnswerUseCase;

public class PresentAnswerController implements Controller {

	public void handle(ParsedUserRequest parsedUserRequest) {
		Request presentQuestionRequest = 
				makePresentAnswerRequest();
		PresentAnswerUseCase presentQuestionUseCase = 
				new PresentAnswerUseCase();
		presentQuestionUseCase.execute(presentQuestionRequest);
	}
	
	public <K,V> Request makePresentAnswerRequest(){
		RequestBuilder requestBuilder = new RequestBuilderImpl();
		return requestBuilder.build("presentAnswer", new HashMap<K,V>());
	}

}
