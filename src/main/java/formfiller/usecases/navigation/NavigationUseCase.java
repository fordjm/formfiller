package formfiller.usecases.navigation;

import formfiller.entities.Prompt;
import formfiller.ApplicationContext;

public class NavigationUseCase implements NavigationRequestBoundary {

	public NavigationUseCase() { }
	
	public void requestNavigation(NavigationRequest navigationRequest) {
		navigateByIndexOffset(navigationRequest.getOffset());
	}
	private void navigateByIndexOffset(int indexOffset) {	
		Prompt currentQuestion = getCurrentQuestion();
		if (indexOffset > 0 && currentQuestion.requiresAnswer() && 
				!hasAnswer(currentQuestion)) throw new AnswerRequired();
		ApplicationContext.questionGateway.findQuestionByIndexOffset(indexOffset);
	}	
	private Prompt getCurrentQuestion() {
		return ApplicationContext.questionGateway.getQuestion();
	}
	private boolean hasAnswer(Prompt currentQuestion) {
		// TODO:  Implement this for real somewhere.
		return false;
	}
	
	public class AnswerRequired extends RuntimeException{ }
}
