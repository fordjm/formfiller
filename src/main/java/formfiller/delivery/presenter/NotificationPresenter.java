package formfiller.delivery.presenter;

import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.response.models.NotificationResponseModel;

/**
 * NotificationPresenter presents Response Models for objects which lack 
 * their own views (i.e. error messages.)
 */
public class NotificationPresenter {	
	private NotificationViewModel viewModel;

	public NotificationViewModel getViewModel() {
		return viewModel;
	}
	
	public void present(NotificationResponseModel response) {
		viewModel = createViewModel(response);
	}
	
	private NotificationViewModel createViewModel(NotificationResponseModel response) {
		NotificationViewModel result = new NotificationViewModel();
		result.message = response.message;
		return result;
	}
	
}
