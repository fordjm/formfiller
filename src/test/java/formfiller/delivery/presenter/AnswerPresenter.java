package formfiller.delivery.presenter;

import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.boundaryCrossers.PresentableResponse;

public class AnswerPresenter extends AbstractPresenter {
	PresentableAnswer presentableAnswer;

	public PresentableAnswer getPresentableResponse() {
		return presentableAnswer;
	}
	
	public void present(PresentableAnswer presentableAnswer) {
		super.present(presentableAnswer);
	}	
	
	protected void setPresentableResponseFieldValue(PresentableResponse presentableResponse) {
		presentableAnswer = (PresentableAnswer) presentableResponse;
	}
}
