package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.appBoundaries.Presenter;
import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableResponse;

public class ResponsePresenter extends Observable implements Presenter {	
	private ViewModel viewModel;
	private PresentableResponse presentableResponse;
	
	public ResponsePresenter(ViewModel viewModel){
		this.viewModel = viewModel;
		clearPresentableResponse();
	}

	public PresentableResponse getPresentableResponse() {
		return presentableResponse;
	}
	
	public void present(PresentableResponse presentableResponse) {
		this.presentableResponse = presentableResponse;
		viewModel.outputPresentableResponse(presentableResponse);
	}
	
	public void clearPresentableResponse() {
		presentableResponse = new PresentableResponse();
	}
}
