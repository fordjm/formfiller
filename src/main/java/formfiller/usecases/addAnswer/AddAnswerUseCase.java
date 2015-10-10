package formfiller.usecases.addAnswer;

import formfiller.Context;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.AnswerImpl;
import formfiller.entities.formComponent.FormComponent;
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
		AnswerImpl answer = makeAnswer(content);
		
		FormComponent foundComponent = Context.formComponentGateway.find(questionId);
		foundComponent.answer = answer;	// TODO:	Should this work?
		//	TODO:	Otherwise, inform the user why the answer could not be added.
	}
	
	private AnswerImpl makeAnswer(Object content){
		AnswerImpl result = new AnswerImpl();
		result.setContent(content);
		return result;
	}
}
