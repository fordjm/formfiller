package formfiller.boundaries;

import formfiller.usecases.PresentableQuestion;
import formfiller.usecases.PresentableQuestionFactoryImpl;

public interface QuestionPresentation {
	public PresentableQuestion presentableQuestion = 
			new PresentableQuestionFactoryImpl().makePresentableQuestion();

	public void presentQuestion();
}
