package formfiller.delivery.presenter;

import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.boundaryCrossers.PresentableResponseImpl;

public class NavigationPresenter extends AbstractPresenter {
	PresentableResponseImpl presentableNavigation;

	public PresentableResponseImpl getPresentableResponse() {
		return presentableNavigation;
	}
	public void present(PresentableResponseImpl presentableNavigation) {
		super.present(presentableNavigation);
	}	
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableNavigation = (PresentableResponseImpl) presentableResponse;
	}

}
