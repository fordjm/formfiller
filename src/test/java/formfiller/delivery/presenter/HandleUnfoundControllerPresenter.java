package formfiller.delivery.presenter;

import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.AbstractPresenter;

public class HandleUnfoundControllerPresenter extends AbstractPresenter {
	PresentableResponse presentableHandleUnfoundController;

	public PresentableResponse getPresentableResponse() {
		return presentableHandleUnfoundController;
	}
	public void present(PresentableResponse presentableHandleUnfoundController) {
		super.present(presentableHandleUnfoundController);
	}
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableHandleUnfoundController = presentableResponse;
	}

}
