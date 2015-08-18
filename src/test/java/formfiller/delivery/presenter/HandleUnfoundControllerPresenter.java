package formfiller.delivery.presenter;

import formfiller.boundaryCrossers.PresentableHandleUnfoundController;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.AbstractPresenter;

public class HandleUnfoundControllerPresenter extends AbstractPresenter {
	PresentableHandleUnfoundController presentableHandleUnfoundController;

	public PresentableHandleUnfoundController getPresentableResponse() {
		return presentableHandleUnfoundController;
	}
	public void present(PresentableHandleUnfoundController presentableHandleUnfoundController) {
		super.present(presentableHandleUnfoundController);
	}
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableHandleUnfoundController = 
				(PresentableHandleUnfoundController) presentableResponse;
	}

}
