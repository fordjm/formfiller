package formfiller.usecases.presentQuestion;

import formfiller.ApplicationContext;
import formfiller.boundaries.PresentQuestionRequestBoundary;
import formfiller.entities.Prompt;
import formfiller.usecases.presentQuestion.PresentQuestionRequestFactoryImpl.PresentQuestionRequest;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class PresentQuestionUseCase implements PresentQuestionRequestBoundary {
	
	public PresentQuestionUseCase(){ }	

	public void presentQuestion(PresentQuestionRequest presentQuestionRequest){
		Prompt requestedQuestion = 
				ApplicationContext.questionGateway.findQuestionByIndexOffset(0);
		PresentableQuestion presentableQuestion = makePresentableQuestion(requestedQuestion);
		ApplicationContext.presentQuestionResponseBoundary.presentQuestion(presentableQuestion);
	}

	PresentableQuestion makePresentableQuestion(Prompt requestedQuestion) {
		PresentableQuestionFactory factory = new PresentableQuestionFactoryImpl();
		PresentableQuestion result = factory.makePresentableQuestion();
		result.setId(requestedQuestion.getId());
		result.setContent(requestedQuestion.getContent());
		return result;
	}
}