package formfiller.usecases;

import formfiller.entities.Prompt;
import formfiller.gateways.ApplicationContext;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class PresentQuestionUseCase {
	public PresentableQuestion presentQuestion(){
		Prompt currentQuestion = ApplicationContext.questionGateway.getQuestion();
		return QuestionPresenter.formatQuestion(currentQuestion);
	}
}