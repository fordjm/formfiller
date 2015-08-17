package formfiller.usecases;

import formfiller.entities.Answer;
import formfiller.ApplicationContext;

public class PresentAnswerUseCase {
	public PresentableAnswer presentAnswer() {
		Answer answer = ApplicationContext.answerGateway.getAnswer();
		return new PresentableAnswer(answer.getId(), answer.getContent());
	}
}
