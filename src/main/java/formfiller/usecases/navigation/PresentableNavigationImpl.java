package formfiller.usecases.navigation;

public class PresentableNavigationImpl implements PresentableNavigation{
	NavigationOutcome outcome;
	String message;
	
	public PresentableNavigationImpl(NavigationOutcome outcome, String message){
		this.outcome = outcome;
		this.message = message;
	}

	public boolean failed(){
		return outcome == NavigationOutcome.FAILED;
	}
	public String getMessage() {
		return message;
	}
}