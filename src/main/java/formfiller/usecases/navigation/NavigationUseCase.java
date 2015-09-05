package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.appBoundaries.Presenter;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.Answer;
import formfiller.entities.ExecutedUseCase;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.ActionOutcome;
import formfiller.enums.Direction;
import formfiller.gateways.Transporter;
import formfiller.request.models.NavigationRequest;
import formfiller.request.models.Request;
import formfiller.response.models.PresentableAnswer;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableQuestion;
import formfiller.response.models.PresentableResponse;

public class NavigationUseCase implements UseCase {
	private NavigationValidator navigationValidator = new NavigationValidator();
	
	public void execute(Request request) {		
		if (request == null) request = new NavigationRequest();

		NavigationRequest navigationRequest = (NavigationRequest) request;
		Direction direction = navigationRequest.direction;
		PresentableResponse response;
		
		if (navigationValidator.isValidMove(direction)) {
			executeMove(direction);
			response = makePresentableFormComponent();
		} else {
			response = makePresentableResponse();
		}
		
		presentNavigationResponse(response);
		ApplicationContext.executedUseCases.push(
				makeExecutedUseCase(response));
	}
	
	private ExecutedUseCase makeExecutedUseCase(PresentableResponse response){
		ExecutedUseCase result = new ExecutedUseCase();
		result.useCase = this;
		result.outcome = response.outcome;
		result.message = response.message;
		return result;
	}
	
	private String getAnswerRequiredMessage(){
		return "Sorry, you cannot move ahead.  "
				+ "The current question requires an answer.";
	}

	private void executeMove(Direction direction) {
		getTransporter().move(direction);
	}

	private Transporter getTransporter() {
		return ApplicationContext.formComponentGateway.
				getTransporter();
	}

	private void presentNavigationResponse(PresentableResponse presentableResponse) {
		getPresenterFromContext(presentableResponse.outcome).
				present(presentableResponse);
	}

	private Presenter getPresenterFromContext(ActionOutcome outcome) {
		if (outcome == ActionOutcome.SUCCEEDED)
			return ApplicationContext.formComponentPresenter;
		else
			return ApplicationContext.responsePresenter;
	}

	//	TODO:	Navigation should know nothing about PresentableResponses.
	//			Make some factories.	
	private PresentableResponse makePresentableResponse() {
		PresentableResponse result = new PresentableResponse();
		result.message = getAnswerRequiredMessage();
		result.outcome = ActionOutcome.FAILED;
		return result;
	}
	
	private PresentableResponse makePresentableFormComponent() {		
		FormComponent current = getCurrentFormComponent();
		PresentableFormComponent result = new PresentableFormComponent();
		result.question = makePresentableQuestion(current.question);
		result.answer = makePresentableAnswer(current.answer);
		result.outcome = ActionOutcome.SUCCEEDED;
		result.message = result.question.message;
		return result;
	}

	private FormComponent getCurrentFormComponent() {
		Transporter transporter = getTransporter();
		return transporter.getCurrent();
	}

	private PresentableQuestion makePresentableQuestion(Prompt requestedQuestion) {
		PresentableQuestion result = new PresentableQuestion();
		result.id = requestedQuestion.getId();
		result.message = requestedQuestion.getContent();
		return result;
	}
	
	private PresentableAnswer makePresentableAnswer(Answer requestedAnswer) {
		PresentableAnswer result = new PresentableAnswer();
		result.id = requestedAnswer.getId();
		result.message = requestedAnswer.getContent().toString();
		return result;
	}
}
