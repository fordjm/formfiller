package formfiller.delivery.presenter;

import formfiller.boundaries.NavigationPresenter;
import formfiller.boundaryCrossers.PresentableNavigation;
import formfiller.boundaryCrossers.PresentableResponse;

public class NavigationPresenterImpl extends AbstractPresenter implements NavigationPresenter {
	PresentableNavigation presentableNavigation;

	public PresentableNavigation getPresentableResponse() {
		return presentableNavigation;
	}
	public void setPresentableResponse(PresentableNavigation presentableNavigation) {
		super.setPresentableResponse(presentableNavigation);
	}	
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableNavigation = (PresentableNavigation) presentableResponse;
	}

}
