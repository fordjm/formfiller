package formfiller.boundaries;

import formfiller.boundaryCrossers.PresentableHandleUnfoundController;

public interface HandleUnfoundControllerPresenter extends Presenter {
	public PresentableHandleUnfoundController getPresentableResponse();
	public void setPresentableResponse(PresentableHandleUnfoundController response);
}
