package formfiller.usecases.presentAnswer;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.boundaryCrossers.PresentableAnswer;
import formfiller.entities.Answer;
import formfiller.request.interfaces.Request;

public class PresentAnswerUseCase implements UseCase {
	
	// Isolating the request that we never use.
	public void execute(Request request){
		execute();
	}

	protected void execute() {
		Answer requestedAnswer = ApplicationContext.answerGateway.getAnswer();
		PresentableAnswer presentableAnswer = makePresentableAnswer(requestedAnswer);
		ApplicationContext.answerPresenter.present(presentableAnswer);
	}
	private PresentableAnswer makePresentableAnswer(Answer requestedAnswer) {
		PresentableAnswer result = new PresentableAnswer();
		result.setId(requestedAnswer.getId());
		result.setMessage(requestedAnswer.getContent().toString());
		return result;
	}
}
