package formfiller.delivery.presenter;

import formfiller.ApplicationContext;
import formfiller.enums.ActionOutcome;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableResponse;

public class NavigationPresenter extends AbstractPresenter {
	PresentableResponse presentableNavigation;

	public PresentableResponse getPresentableResponse() {
		return presentableNavigation;
	}
	
	public void present(PresentableResponse presentableNavigation) {
		super.present(presentableNavigation);
		if (presentableNavigation.outcome == ActionOutcome.SUCCEEDED)
			presentSuccessfulNavigation(presentableNavigation);
		// else presentUseCaseError();
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
