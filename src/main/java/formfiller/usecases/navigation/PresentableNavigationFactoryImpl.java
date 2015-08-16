package formfiller.usecases.navigation;

public class PresentableNavigationFactoryImpl implements PresentableNavigationFactory {

	public PresentableNavigation makePresentableNavigation(NavigationOutcome outcome, String message) {
		return new PresentableNavigationImpl(outcome, message);

	}

	public class PresentableNavigationImpl implements PresentableNavigation{
		NavigationOutcome outcome;
		String message;
		
		private PresentableNavigationImpl(NavigationOutcome outcome, String message){
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
}
