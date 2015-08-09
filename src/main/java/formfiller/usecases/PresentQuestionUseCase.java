package formfiller.usecases;

import formfiller.entities.Prompt;
import formfiller.gateways.Context;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class PresentQuestionUseCase {
	public PresentableQuestion presentQuestion(){
		Prompt currentQuestion = Context.questionGateway.getQuestion();
		return new PresentableQuestion(currentQuestion.getId(), currentQuestion.getContent());
	}
}