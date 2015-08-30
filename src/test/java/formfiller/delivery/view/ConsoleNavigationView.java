package formfiller.delivery.view;

import java.util.Observable;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.View;
import formfiller.enums.ActionOutcome;

public class ConsoleNavigationView implements View {

	public void update(Observable observable, Object input) {
		outputPresentableResponse();
	}
	
	public void outputPresentableResponse() {
		PresentableResponse presentableNavigation = 
				ApplicationContext.navigationPresenter.getPresentableResponse();
		if (presentableNavigation.getOutcome() == ActionOutcome.FAILED)
			outputFailedNavigation(presentableNavigation);
	}
	
	private void outputFailedNavigation(PresentableResponse presentableNavigation) {
		System.out.println(presentableNavigation.getMessage());
	}	
}
