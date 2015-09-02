package formfiller.delivery.viewModel;

import java.util.Observable;

import formfiller.delivery.ViewModel;
import formfiller.delivery.ui.testConsoleUi.ConsoleView;
import formfiller.enums.ActionOutcome;
import formfiller.response.models.PresentableResponse;

// TODO:	Change to FailedUseCaseViewModel
public class NavigationViewModel implements ViewModel {

	public void update(Observable observable, Object input) {
		outputPresentableResponse(input);
	}
	
	public void outputPresentableResponse(Object input) {
		PresentableResponse presentableNavigation = 
				(PresentableResponse) input;
		if (presentableNavigation.outcome == ActionOutcome.FAILED)
			outputFailedNavigation(presentableNavigation);
	}
	
	private void outputFailedNavigation(PresentableResponse presentableNavigation) {
		ConsoleView.output(presentableNavigation.message);
	}	
}
