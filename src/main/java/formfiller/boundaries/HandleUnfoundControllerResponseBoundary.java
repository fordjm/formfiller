package formfiller.boundaries;

import formfiller.boundaryCrossers.PresentableHandleUnfoundController;

public interface HandleUnfoundControllerResponseBoundary {
	public PresentableHandleUnfoundController getPresentableResponse();
	public void setPresentableResponse(PresentableHandleUnfoundController response);
}
