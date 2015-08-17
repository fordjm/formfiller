package formfiller.delivery.presenter;

import formfiller.boundaries.QuestionPresenter;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.boundaryCrossers.PresentableResponse;

public class QuestionPresenterImpl extends AbstractPresenter implements QuestionPresenter {
	PresentableQuestion presentableQuestion;
	
	public QuestionPresenterImpl(){	}

	public PresentableQuestion getPresentableResponse() {
		return presentableQuestion;
	}
	public void setPresentableResponse(PresentableQuestion presentableQuestion) {
		super.setPresentableResponse(presentableQuestion);
	}
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableQuestion = (PresentableQuestion) presentableResponse;
	}
	
}
