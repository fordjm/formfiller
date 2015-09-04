package formfiller.delivery.presenter;

import formfiller.ApplicationContext;
import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableResponse;

public class FormComponentPresenter extends ResponsePresenter {
	PresentableFormComponent component;
	
	public FormComponentPresenter(ViewModel viewModel) {
		super(viewModel);
	}

	public PresentableResponse getPresentableResponse() {
		return component;
	}
	
	public void present(PresentableResponse presentableFormComponent) {
		if (presentableFormComponent == null) throw new ResponsePresenter.NullPresentableResponse();
		
		component = (PresentableFormComponent) presentableFormComponent;
		ApplicationContext.questionPresenter.present(component.question);
		ApplicationContext.answerPresenter.present(component.answer);
	}

	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		//presentableFormComponent = (PresentableFormComponent) presentableResponse;
	}
}
