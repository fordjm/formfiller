package formfiller.usecases.navigation;

import formfiller.entities.Prompt;
import formfiller.usecases.Request;
import formfiller.usecases.UseCase;
import formfiller.ApplicationContext;

public class NavigationUseCase implements NavigationRequestBoundary, UseCase {

	public NavigationUseCase() { }
	
	public void execute(Request request) {
		// try goes here
		NavigationRequest navigationRequest = (NavigationRequest) request;
		// throwable code starts here (LocalNavigation object?)
		navigateByIndexOffset(navigationRequest.getOffset());
		// catch goes here
	}
	private void navigateByIndexOffset(int indexOffset) {	
		Prompt currentQuestion = getCurrentQuestion();
		if (indexOffset > 0 && currentQuestion.requiresAnswer() && 
				!currentQuestion.hasAnswer()) throw new AnswerRequired();
		ApplicationContext.questionGateway.findQuestionByIndexOffset(indexOffset);
	}	
	private Prompt getCurrentQuestion() {
		return ApplicationContext.questionGateway.getQuestion();
	}
	
	public class AnswerRequired extends RuntimeException{ }
}
