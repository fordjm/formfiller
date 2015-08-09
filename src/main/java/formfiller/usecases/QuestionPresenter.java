package formfiller.usecases;

import formfiller.entities.Prompt;

public class QuestionPresenter {

	public static PresentableQuestion formatQuestion(Prompt currentQuestion) {
		return new PresentableQuestion(
				currentQuestion.getId(), currentQuestion.getContent());
	}

}
