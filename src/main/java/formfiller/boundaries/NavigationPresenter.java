package formfiller.boundaries;

import formfiller.boundaryCrossers.PresentableNavigation;

public interface NavigationPresenter extends Presenter {

	PresentableNavigation getPresentableResponse();
	void setPresentableResponse(PresentableNavigation presentableNavigation);

}
