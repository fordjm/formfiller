package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.appBoundaries.Presenter;
import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableResponse;

public class ResponsePresenter extends Observable implements Presenter {	
	ViewModel viewModel;
	PresentableResponse presentableResponse;
	
	public ResponsePresenter(ViewModel viewModel){
		this.viewModel = viewModel;
	}

	public PresentableResponse getPresentableResponse() {
		return presentableResponse;
	}
	
	public void present(PresentableResponse presentableResponse) {
		this.presentableResponse = presentableResponse;
		viewModel.outputPresentableResponse(presentableResponse);
	}
	
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		this.presentableResponse = presentableResponse;
	}

	@SuppressWarnings("serial")
	public class NullPresentableResponse extends RuntimeException { }
}
