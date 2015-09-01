package formfiller.delivery.presenter;

import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.boundaryCrossers.PresentableResponse;

public class QuestionPresenter extends AbstractPresenter {
	PresentableQuestion presentableQuestion;
	
	public QuestionPresenter(){	}

	public PresentableQuestion getPresentableResponse() {
		return presentableQuestion;
	}
	
	public void present(PresentableQuestion presentableQuestion) {
		super.present(presentableQuestion);
	}
	
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableQuestion = (PresentableQuestion) presentableResponse;
	}	
}
