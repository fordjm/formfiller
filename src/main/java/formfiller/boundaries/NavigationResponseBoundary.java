package formfiller.boundaries;

import formfiller.boundaryCrossers.PresentableNavigation;

public interface NavigationResponseBoundary extends Presenter {

	PresentableNavigation getPresentableResponse();
	void setPresentableResponse(PresentableNavigation presentableNavigation);

}
