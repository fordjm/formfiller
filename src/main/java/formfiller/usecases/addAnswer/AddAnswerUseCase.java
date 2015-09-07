package formfiller.usecases.addAnswer;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.ConstrainableAnswer;
import formfiller.entities.FormComponent;
import formfiller.request.models.AddAnswerRequest;
import formfiller.request.models.Request;

public class AddAnswerUseCase implements UseCase {

	public void execute(Request request) {
		AddAnswerRequest addAnswerRequest = (AddAnswerRequest) request;
		String questionId = addAnswerRequest.questionId;
		Object content = addAnswerRequest.content;
		//	If the answer content satisfies the answer constraints
		if (content == "") return;
		//		Then add the answer at the Gateway and tell the user.
		ConstrainableAnswer answer = new ConstrainableAnswer(content);
		
		FormComponent foundComponent = FormFillerContext.formComponentGateway.find(questionId);
		
		FormFillerContext.formComponentGateway.find(questionId).answer = answer;
		
		foundComponent = FormFillerContext.formComponentGateway.find(questionId);
		//	TODO:	Otherwise, inform the user why the answer could not be added.
	}
}
