package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.PresentableResponseImpl;
import formfiller.entities.ExecutedUseCaseImpl;
import formfiller.entities.Prompt;
import formfiller.enums.ActionOutcome;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.request.interfaces.Request;

public class NavigationUseCase implements UseCase {
	private ActionOutcome outcome;
	private String message;
	
	public void execute(Request request) {
		NavigationRequest navigationRequest = (NavigationRequest) request;
		navigateByIndexOffset(navigationRequest.getOffset());
		ApplicationContext.executedUseCases.push(
				new ExecutedUseCaseImpl(this, outcome, message));
	}
	
	private void navigateByIndexOffset(int indexOffset) {	
		PresentableResponse presentableResponse;
		if (isIndexAdvancing(indexOffset) && isAnswerRequiredButAbsent()){
			setOutcome(ActionOutcome.FAILED);
			setMessage(getAnswerRequiredMessage());
		}
		else{
			setOutcome(ActionOutcome.SUCCEEDED);
			ApplicationContext.questionGateway.findQuestionByIndexOffset(indexOffset);
			setMessage("");
		}
		presentableResponse = makePresentableNavigation();
		ApplicationContext.navigationPresenter.present(presentableResponse);
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
	private Prompt getCurrentQuestion() {
		return ApplicationContext.questionGateway.getQuestion();
	}
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires a response.";
	}
	private void setOutcome(ActionOutcome actionOutcome) {
		outcome = actionOutcome;
	}	
	private void setMessage(String message) {
		this.message = message;
	}
	private PresentableResponse makePresentableNavigation() {
		PresentableResponse result = new PresentableResponseImpl();
		result.setMessage(message);
		result.setOutcome(outcome);
		return result;
	}
}
