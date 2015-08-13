package formfiller.boundaries;

import formfiller.usecases.presentQuestion.PresentQuestionRequestFactoryImpl.PresentQuestionRequest;

public interface PresentQuestionRequestBoundary {

	public void presentQuestion(PresentQuestionRequest presentQuestionRequest);
}
