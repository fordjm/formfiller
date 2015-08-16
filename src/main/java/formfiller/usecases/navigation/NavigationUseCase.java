package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.entities.Prompt;
import formfiller.usecases.Request;
import formfiller.usecases.UseCase;

public class NavigationUseCase implements UseCase {
	
	public NavigationUseCase() { }
	
	public void execute(Request request) {
		NavigationRequest navigationRequest = (NavigationRequest) request;
		navigateByIndexOffset(navigationRequest.getOffset());
	}
	private void navigateByIndexOffset(int indexOffset) {	
		PresentableNavigation presentableNavigation;
		if (isIndexAdvancing(indexOffset) && isAnswerRequiredButAbsent()){
			presentableNavigation = makeFailedPresentableNavigation();
		}
		else{
			ApplicationContext.questionGateway.findQuestionByIndexOffset(indexOffset);
			presentableNavigation = makeSucceededPresentableNavigation();
		}
		ApplicationContext.navigationResponseBoundary.
				setPresentableNavigation(presentableNavigation);
	}
	private boolean isIndexAdvancing(int indexOffset) {
		return indexOffset > 0;
	}
	private boolean isAnswerRequiredButAbsent(){
		boolean result;
		Prompt currentQuestion = getCurrentQuestion();
		result = currentQuestion.requiresAnswer() && 
				!currentQuestion.hasAnswer();
		return result;
	}
	
	// TODO:  Replace real PresentableNavigations with mocks
	private PresentableNavigation makeFailedPresentableNavigation() {
		return makePresentableNavigation(
				NavigationOutcome.FAILED, getAnswerRequiredMessage());
	}
	private PresentableNavigation makeSucceededPresentableNavigation() {
		return makePresentableNavigation(
				NavigationOutcome.SUCCESSFUL, "");
	}
	private PresentableNavigation makePresentableNavigation(
			NavigationOutcome navigationOutcome, String message) {
		PresentableNavigationFactory factory = new PresentableNavigationFactoryImpl();
		return factory.makePresentableNavigation(navigationOutcome, message);
	}	
	private Prompt getCurrentQuestion() {
		return ApplicationContext.questionGateway.getQuestion();
	}
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires a response.";
	}
}
