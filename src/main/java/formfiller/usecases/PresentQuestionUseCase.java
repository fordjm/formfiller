package formfiller.usecases;

import formfiller.entities.Prompt;
import formfiller.gateways.ApplicationContext;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class PresentQuestionUseCase {
	private PresentableQuestionFactory factory;
	
	public PresentQuestionUseCase(){
		factory = new PresentableQuestionFactoryImpl();
	}	
	public void requestQuestion(){
		Prompt requestedQuestion = 
				ApplicationContext.questionGateway.findQuestionByIndexOffset(0);
		ApplicationContext.presentQuestionBoundary.setQuestion(
				factory.makePresentableQuestion(requestedQuestion));
	}
}