package formfiller.usecases.navigation;

public interface PresentableNavigationFactory {

	PresentableNavigation makePresentableNavigation(NavigationOutcome outcome, String message);

}