package formfiller.usecases.presentQuestion;

import formfiller.Controller;
import formfiller.ui.userRequestParser.ParsedUserRequest;
import formfiller.usecases.presentQuestion.PresentQuestionRequestFactoryImpl.PresentQuestionRequest;

public class PresentQuestionController implements Controller {

	public void handle(ParsedUserRequest parsedInput) {
		PresentQuestionRequest presentQuestionRequest = 
				makePresentQuestionRequest();
		PresentQuestionUseCase presentQuestionUseCase = 
				new PresentQuestionUseCase();
		presentQuestionUseCase.presentQuestion(presentQuestionRequest);
	}
	
	public PresentQuestionRequest makePresentQuestionRequest(){
		PresentQuestionRequestFactoryImpl factory = 
				new PresentQuestionRequestFactoryImpl();
		PresentQuestionRequest result = 
				factory.makePresentQuestionRequest();
		return result;
	}

}
