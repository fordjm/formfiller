package formfiller.usecases.presentQuestion;

import formfiller.ApplicationContext;
import formfiller.entities.Prompt;
import formfiller.usecases.Request;
import formfiller.usecases.UseCase;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class PresentQuestionUseCase implements UseCase {
	
	public PresentQuestionUseCase(){ }	

	public void execute(Request request){
		Prompt requestedQuestion = 
				ApplicationContext.questionGateway.findQuestionByIndexOffset(0);
		PresentableQuestion presentableQuestion = makePresentableQuestion(requestedQuestion);
		ApplicationContext.presentQuestionResponseBoundary.
				setPresentableQuestion(presentableQuestion);
	}

	PresentableQuestion makePresentableQuestion(Prompt requestedQuestion) {
		PresentableQuestion result = new PresentableQuestionImpl();
		result.setId(requestedQuestion.getId());
		result.setContent(requestedQuestion.getContent());
		return result;
	}
}