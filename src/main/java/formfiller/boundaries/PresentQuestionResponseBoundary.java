package formfiller.boundaries;

import formfiller.usecases.presentQuestion.PresentableQuestion;

public interface PresentQuestionResponseBoundary {

	public PresentableQuestion getPresentableQuestion();
	public void setPresentableQuestion(PresentableQuestion presentableQuestion);
	
}
