package formfiller.boundaries;

import formfiller.boundaryCrossers.PresentableQuestion;

public interface PresentQuestionResponseBoundary {

	public PresentableQuestion getPresentableResponse();
	public void setPresentableQuestion(PresentableQuestion presentableQuestion);
	
}
