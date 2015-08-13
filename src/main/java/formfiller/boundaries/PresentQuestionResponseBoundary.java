package formfiller.boundaries;

import formfiller.usecases.presentQuestion.PresentableQuestion;

public interface PresentQuestionResponseBoundary {

	public void presentQuestion(PresentableQuestion presentableQuestion);
	public PresentableQuestion getPresentableQuestion();
}
