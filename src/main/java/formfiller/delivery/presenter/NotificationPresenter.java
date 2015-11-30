package formfiller.delivery.presenter;

import formfiller.appBoundaries.Presenter;
import formfiller.delivery.ViewModel;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.response.models.PresentableResponse;

public class NotificationPresenter implements Presenter {	
	private ViewModel viewModel;
	private PresentableResponse presentableResponse;
	
	public NotificationPresenter(){
		clearPresentableResponse();
	}

	//	TODO:	Change to getViewModel()
	public ViewModel getViewModel() {
		return viewModel;
	}
	
	public void present(PresentableResponse presentableResponse) {
		viewModel = createViewModel(presentableResponse);
		
		this.presentableResponse = presentableResponse;
	}
	
	private ViewModel createViewModel(PresentableResponse presentableResponse) {
		NotificationViewModel result = new NotificationViewModel();
		result.message = presentableResponse.message;
		return result;
	}

	public void clearPresentableResponse() {
		presentableResponse = new PresentableResponse();
	}
	
}
