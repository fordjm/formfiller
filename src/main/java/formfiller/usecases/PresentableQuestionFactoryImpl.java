package formfiller.usecases;

import formfiller.entities.Prompt;

public class PresentableQuestionFactoryImpl implements PresentableQuestionFactory {
	public PresentableQuestion makePresentableQuestion(Prompt questionObject){
		return new PresentableQuestion(questionObject.getId(), questionObject.getContent());
	}
}
