package formfiller.delivery.presenter;

import formfiller.boundaryCrossers.PresentableNavigation;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.AbstractPresenter;

public class NavigationPresenter extends AbstractPresenter {
	PresentableNavigation presentableNavigation;

	public PresentableNavigation getPresentableResponse() {
		return presentableNavigation;
	}
	public void present(PresentableNavigation presentableNavigation) {
		super.present(presentableNavigation);
	}	
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableNavigation = (PresentableNavigation) presentableResponse;
	}

}
