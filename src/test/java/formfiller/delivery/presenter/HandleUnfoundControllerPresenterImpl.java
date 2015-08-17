package formfiller.delivery.presenter;

import formfiller.boundaries.HandleUnfoundControllerPresenter;
import formfiller.boundaryCrossers.PresentableHandleUnfoundController;
import formfiller.boundaryCrossers.PresentableResponse;

public class HandleUnfoundControllerPresenterImpl extends AbstractPresenter 
			implements HandleUnfoundControllerPresenter {
	PresentableHandleUnfoundController presentableHandleUnfoundController;

	public PresentableHandleUnfoundController getPresentableResponse() {
		return presentableHandleUnfoundController;
	}
	public void setPresentableResponse(PresentableHandleUnfoundController presentableHandleUnfoundController) {
		super.setPresentableResponse(presentableHandleUnfoundController);
	}
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableHandleUnfoundController = 
				(PresentableHandleUnfoundController) presentableResponse;
	}

}
