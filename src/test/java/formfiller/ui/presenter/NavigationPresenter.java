package formfiller.ui.presenter;

import java.util.Observable;

import formfiller.boundaries.NavigationResponseBoundary;
import formfiller.usecases.navigation.PresentableNavigation;

public class NavigationPresenter extends Observable implements NavigationResponseBoundary {
	PresentableNavigation presentableNavigation;

	public PresentableNavigation getPresentableNavigation() {
		return presentableNavigation;
	}
	public void setPresentableNavigation(PresentableNavigation presentableNavigation) {
		this.presentableNavigation = presentableNavigation;
		setChanged();
		notifyObservers();
	}

}
