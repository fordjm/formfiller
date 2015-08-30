package formfiller.usecases.addAnswer;

import formfiller.ApplicationContext;
import formfiller.boundaries.UseCase;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.request.implementations.AddAnswerRequest;
import formfiller.request.interfaces.Request;

public class AddAnswerUseCase implements UseCase {

	public void execute(Request request) {
		AddAnswerRequest addAnswerRequest = (AddAnswerRequest) request;
		String questionId = addAnswerRequest.getQuestionId();
		Object content = addAnswerRequest.getContent();
		//	If the answer content satisfies the answer constraints
		if (content == "") return;
		//		Then add the answer at the Gateway and tell the user.
		Answer answer = new AnswerImpl(content);
		ApplicationContext.formComponentGateway.find(questionId).setAnswer(answer);
		//	Otherwise, inform the user why the answer could not be added.
	}

}
