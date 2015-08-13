package formfiller.usecases.navigation;

import formfiller.entities.Prompt;
import formfiller.ApplicationContext;

public class NavigationUseCase implements NavigationRequestBoundary {

	public NavigationUseCase() { }

	public void navigateToPrevQuestion(){
		navigateByIndexOffset(-1);
	}
	public void navigateToCurrentQuestion(){
		navigateByIndexOffset(0);
	}
	public void navigateToNextQuestion(){
		navigateByIndexOffset(1);
	}
	public void navigateByIndexOffset(int indexOffset) {		
		if (currentQuestionRequiresResponse()) throw new ResponseRequired();
		ApplicationContext.questionGateway.findQuestionByIndexOffset(indexOffset);
	}	
	public boolean currentQuestionRequiresResponse(){
		Prompt currentQuestion = ApplicationContext.questionGateway.getQuestion();
		return currentQuestion.requiresAnswer();
	}
	public void requestNavigation(NavigationRequest navigationRequest) {
		navigateByIndexOffset(navigationRequest.getOffset());
	}
	
	public class ResponseRequired extends RuntimeException{ }
}
