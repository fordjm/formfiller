package formfiller.delivery.presenter;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableFormComponent;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.enums.ActionOutcome;

public class NavigationPresenter extends AbstractPresenter {
	PresentableResponse presentableNavigation;

	public PresentableResponse getPresentableResponse() {
		return presentableNavigation;
	}
	
	public void present(PresentableResponse presentableNavigation) {
		super.present(presentableNavigation);
		if (presentableNavigation.outcome == ActionOutcome.SUCCEEDED)
			presentSuccessfulNavigation(presentableNavigation);
	}	
	
	private void presentSuccessfulNavigation(PresentableResponse presentableNavigation) {
		PresentableFormComponent component = (PresentableFormComponent) presentableNavigation;
		ApplicationContext.questionPresenter.present(component.question);
		ApplicationContext.answerPresenter.present(component.answer);
	}

	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableNavigation = (PresentableResponse) presentableResponse;
	}
}
