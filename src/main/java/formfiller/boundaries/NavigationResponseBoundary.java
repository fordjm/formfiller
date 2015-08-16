package formfiller.boundaries;

import formfiller.usecases.navigation.PresentableNavigation;

public interface NavigationResponseBoundary {

	PresentableNavigation getPresentableNavigation();
	void setPresentableNavigation(PresentableNavigation presentableNavigation);

}
