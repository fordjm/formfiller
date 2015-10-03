package formfiller.delivery.presenter;

import formfiller.Context;
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
	
	public void present(PresentableResponse componentResponse) {
		if (componentResponse == null) componentResponse = 
				new PresentableFormComponent();
		
		component = (PresentableFormComponent) componentResponse;
		Context.questionPresenter.present(component.question);
		Context.answerPresenter.present(component.answer);
	}
}
