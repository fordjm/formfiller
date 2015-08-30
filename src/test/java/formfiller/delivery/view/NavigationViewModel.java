package formfiller.delivery.view;

import java.util.Observable;

import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.ViewModel;
import formfiller.enums.ActionOutcome;

public class NavigationViewModel implements ViewModel {

	public void update(Observable observable, Object input) {
		outputPresentableResponse(input);
	}
	
	public void outputPresentableResponse(Object input) {
		PresentableResponse presentableNavigation = 
				(PresentableResponse) input;
		if (presentableNavigation.getOutcome() == ActionOutcome.FAILED)
			outputFailedNavigation(presentableNavigation);
	}
	
	private void outputFailedNavigation(PresentableResponse presentableNavigation) {
		ConsoleView.output(presentableNavigation.getMessage());
	}	
}
