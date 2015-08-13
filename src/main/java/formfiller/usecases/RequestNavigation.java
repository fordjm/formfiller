package formfiller.usecases;

public interface RequestNavigation {
	NavigationRequest navigationRequest = 
			new NavigationRequestFactoryImpl().makeNavigationRequest();
	
	public void requestNavigation();
}
