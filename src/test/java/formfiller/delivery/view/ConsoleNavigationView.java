package formfiller.delivery.view;

import java.util.Observable;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.View;
import formfiller.enums.ActionOutcome;

public class ConsoleNavigationView implements View {

	public void update(Observable observable, Object input) {
		displayPresentableResponse();
	}
	public void displayPresentableResponse() {
		PresentableResponse presentableNavigation = 
				ApplicationContext.navigationPresenter.getPresentableResponse();
		if (presentableNavigation.getOutcome() == ActionOutcome.FAILED)
			System.out.println(presentableNavigation.getMessage());
	}
	
}