package formfiller.usecases;

import formfiller.boundaries.QuestionPresentation;
import formfiller.boundaries.RequestPresentableQuestion;
import formfiller.entities.Prompt;
import formfiller.gateways.ApplicationContext;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class PresentQuestionUseCase implements RequestPresentableQuestion {
	
	public PresentQuestionUseCase(){ }	

	public void requestPresentableQuestion(){
		Prompt requestedQuestion = 
				ApplicationContext.questionGateway.findQuestionByIndexOffset(0);
		QuestionPresentation.presentableQuestion.setId(requestedQuestion.getId());
		QuestionPresentation.presentableQuestion.setContent(requestedQuestion.getContent());
	}
}