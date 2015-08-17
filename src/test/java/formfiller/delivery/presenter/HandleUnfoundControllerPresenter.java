package formfiller.delivery.presenter;

import formfiller.boundaries.HandleUnfoundControllerResponseBoundary;
import formfiller.boundaryCrossers.PresentableHandleUnfoundController;

public class HandleUnfoundControllerPresenter implements HandleUnfoundControllerResponseBoundary {
	PresentableHandleUnfoundController response;

	public PresentableHandleUnfoundController getPresentableResponse() {
		return response;
	}
	public void setPresentableResponse(PresentableHandleUnfoundController response) {
		this.response = response;
	}

}
