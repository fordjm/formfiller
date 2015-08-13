package formfiller.usecases.presentQuestion;

import formfiller.usecases.presentQuestion.PresentQuestionRequestFactoryImpl.PresentQuestionRequest;

public class PresentQuestionController {

	public void requestQuestionPresentation() {
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
