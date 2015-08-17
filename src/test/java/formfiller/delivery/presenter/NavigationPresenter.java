package formfiller.delivery.presenter;

import java.util.Observable;

import formfiller.boundaries.NavigationResponseBoundary;
import formfiller.boundaryCrossers.PresentableNavigation;

public class NavigationPresenter extends Observable implements NavigationResponseBoundary {
	PresentableNavigation presentableNavigation;

	public PresentableNavigation getPresentableResponse() {
		return presentableNavigation;
	}
	public void setPresentableResponse(PresentableNavigation presentableNavigation) {
		this.presentableNavigation = presentableNavigation;
		setChanged();
		notifyObservers();
	}

}
