package formfiller.usecases.presentQuestion;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.entities.Prompt;
import formfiller.usecases.Request;

// As presented in the Clean Coders Java Case Study codecast
// https://cleancoders.com/episode/case-study-episode-1/show
// Retrieved 2015-08-06
public class PresentQuestionUseCase implements UseCase {
	
	public PresentQuestionUseCase(){ }	

	// Isolating the request that we never use.
	public void execute(Request request){
		execute();
	}

	protected void execute(){
		Prompt requestedQuestion = 
				ApplicationContext.questionGateway.getQuestion();
		PresentableQuestion presentableQuestion = makePresentableQuestion(requestedQuestion);
		ApplicationContext.questionPresenter.
				present(presentableQuestion);
	}
	PresentableQuestion makePresentableQuestion(Prompt requestedQuestion) {
		PresentableQuestion result = new PresentableQuestion();
		result.setId(requestedQuestion.getId());
		result.setMessage(requestedQuestion.getContent());
		return result;
	}
	
}