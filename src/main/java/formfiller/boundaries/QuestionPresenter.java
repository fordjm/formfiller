package formfiller.boundaries;

import formfiller.boundaryCrossers.PresentableQuestion;

public interface QuestionPresenter extends Presenter {

	public PresentableQuestion getPresentableResponse();
	public void setPresentableResponse(PresentableQuestion presentableQuestion);
	
}
