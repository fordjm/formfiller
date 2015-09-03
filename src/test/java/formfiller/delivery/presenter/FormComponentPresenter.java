package formfiller.delivery.presenter;

import formfiller.ApplicationContext;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableResponse;

public class FormComponentPresenter extends AbstractPresenter {
	PresentableFormComponent presentableFormComponent;

	public PresentableResponse getPresentableResponse() {
		return presentableFormComponent;
	}
	
	public void present(PresentableResponse presentableFormComponent) {
		super.present(presentableFormComponent);
		PresentableFormComponent component = (PresentableFormComponent) presentableFormComponent;
		ApplicationContext.questionPresenter.present(component.question);
		ApplicationContext.answerPresenter.present(component.answer);
	}

	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableFormComponent = (PresentableFormComponent) presentableResponse;
	}
}
