package formfiller.usecases.askQuestion;

import formfiller.EventSinks;
import formfiller.delivery.presenter.NotificationPresenter;
import formfiller.request.models.Request;
import formfiller.response.models.NotificationResponseModel;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.askQuestion.AskQuestionUseCase.RequiredAnswer;

public class LocalAskQuestionUseCase {
	private AskQuestionUseCase useCase;
	
	public LocalAskQuestionUseCase(AskQuestionUseCase useCase) {
		this.useCase = useCase;
	}

	public void execute(Request request) {
		try {
			useCase.execute(request);
		} catch (RequiredAnswer e) {
			NotificationResponseModel response = makeResponse(e.getMessage());
			presentResponse(response);
		}
	}
	
	private NotificationResponseModel makeResponse(String message) {
		NotificationResponseModel response = new NotificationResponseModel();
		response.message = message;
		return response;
	}

	private void presentResponse(NotificationResponseModel response) {
		NotificationPresenter presenter = new NotificationPresenter();
		presenter.present(response);
		EventSinks.receive(presenter.getViewModel());
	}	
	
}
