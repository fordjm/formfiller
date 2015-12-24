package formfiller.usecases.handleUnfoundUseCase;

import formfiller.Context;
import formfiller.EventSinks;
import formfiller.appBoundaries.UseCase;
import formfiller.delivery.presenter.NotificationPresenter;
import formfiller.request.models.HandleUnfoundUseCaseRequest;
import formfiller.request.models.Request;
import formfiller.response.models.NotificationResponseModel;

public class HandleUnfoundUseCaseUseCase implements UseCase {	

	public void execute(Request request) {
		if (request == null) request = new HandleUnfoundUseCaseRequest();
		
		HandleUnfoundUseCaseRequest handleUnfoundUseCaseRequest = 
				(HandleUnfoundUseCaseRequest) request;
		String message = handleUnfoundUseCaseRequest.message;
		presentHandleUnfoundUseCaseNotification(message);
	}

	private void presentHandleUnfoundUseCaseNotification(String message) {
		NotificationResponseModel response = makeResponseModel(message);
		NotificationPresenter presenter = new NotificationPresenter();
		presenter.present(response);
		EventSinks.receive(presenter.getViewModel());
		
		temporarilyPopulateOutcomePresenterForFitNesseFixture(response);
	}
	
	private NotificationResponseModel makeResponseModel(String message) {
		NotificationResponseModel result = new NotificationResponseModel();
		result.message = message;
		return result;
	}	

	private void temporarilyPopulateOutcomePresenterForFitNesseFixture(NotificationResponseModel response) {
		Context.outcomePresenter.present(response);
	}
	
}
