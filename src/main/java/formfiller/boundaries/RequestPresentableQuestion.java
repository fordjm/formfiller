package formfiller.boundaries;

import formfiller.usecases.PresentQuestionRequestFactoryImpl;
import formfiller.usecases.PresentQuestionRequestFactoryImpl.PresentQuestionRequest;

public interface RequestPresentableQuestion {
	PresentQuestionRequest presentQuestionRequest = 
			new PresentQuestionRequestFactoryImpl().makePresentQuestionRequest();

	public void requestPresentableQuestion();
}
