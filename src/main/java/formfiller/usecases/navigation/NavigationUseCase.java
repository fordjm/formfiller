package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableHandleUnfoundController;
import formfiller.boundaryCrossers.PresentableNavigation;
import formfiller.entities.Prompt;
import formfiller.enums.ActionOutcome;
import formfiller.usecases.Request;

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
		ApplicationContext.navigationPresenter.
				present(presentableNavigation);
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
		return makePresentableNavigation(getAnswerRequiredMessage(), 
				ActionOutcome.FAILED);
	}
	private PresentableNavigation makeSucceededPresentableNavigation() {
		return makePresentableNavigation("", ActionOutcome.SUCCEEDED);
	}
	private PresentableNavigation makePresentableNavigation(String message, 
			ActionOutcome outcome) {
		PresentableNavigation result = new PresentableNavigation();
		result.setMessage(message);
		result.setOutcome(outcome);
		return result;
	}
	private Prompt getCurrentQuestion() {
		return ApplicationContext.questionGateway.getQuestion();
	}
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires a response.";
	}
}
