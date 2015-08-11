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
		requestQuestion(0);
	}
	public void requestQuestion(int indexOffset){
		Prompt currentQuestion = 
				ApplicationContext.questionGateway.findQuestionByIndexOffset(indexOffset);
		ApplicationContext.presentQuestionBoundary.setQuestion(
				factory.makePresentableQuestion(currentQuestion));
	}
}