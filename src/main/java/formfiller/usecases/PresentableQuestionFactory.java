package formfiller.usecases;

import formfiller.entities.Prompt;

public interface PresentableQuestionFactory {
	public PresentableQuestion makePresentableQuestion(Prompt questionObject);
}