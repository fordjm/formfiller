package formfiller.delivery.presenter;

import formfiller.boundaryCrossers.PresentableHandleUnfoundController;
import formfiller.boundaryCrossers.PresentableResponse;

public class HandleUnfoundControllerPresenter extends AbstractPresenter {
	PresentableHandleUnfoundController presentableHandleUnfoundController;

	public PresentableHandleUnfoundController getPresentableResponse() {
		return presentableHandleUnfoundController;
	}
	public void setPresentableResponse(PresentableHandleUnfoundController presentableHandleUnfoundController) {
		super.present(presentableHandleUnfoundController);
	}
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableHandleUnfoundController = 
				(PresentableHandleUnfoundController) presentableResponse;
	}

}
