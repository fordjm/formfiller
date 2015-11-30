package formfiller.delivery.presenter;

import formfiller.Context;
import formfiller.appBoundaries.Presenter;
import formfiller.delivery.ViewModel;
import formfiller.delivery.viewModel.FormComponentViewModel;
import formfiller.response.models.PresentableFormComponent;
import formfiller.response.models.PresentableResponse;

public class FormComponentPresenter implements Presenter {	
	private ViewModel viewModel;
	private PresentableResponse presentableResponse;
	PresentableFormComponent component;

	public ViewModel getViewModel() {
		return viewModel;
	}
	
	public void present(PresentableResponse componentResponse) {
		if (componentResponse == null) componentResponse = 
				new PresentableFormComponent();
		
		component = (PresentableFormComponent) componentResponse;
		viewModel = makeViewableFormComponent(component);
		
		//	TODO:	Remove
		Context.questionPresenter.present(component.question);
		Context.answerPresenter.present(component.answer);
	}

	//	TODO:	Continue
	private ViewModel makeViewableFormComponent(PresentableFormComponent component) {
		FormComponentViewModel result = new FormComponentViewModel();
		result.questionMessage = component.question.message;
		result.answerMessage = component.answer.message;
		return result;
	}

	public void clearPresentableResponse() {
		// TODO Auto-generated method stub
		
	}
	
}
