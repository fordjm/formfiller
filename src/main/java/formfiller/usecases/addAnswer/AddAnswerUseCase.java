package formfiller.usecases.addAnswer;

import formfiller.Context;
import formfiller.appBoundaries.UseCase;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddAnswerRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCaseExecution;
import formfiller.utilities.FormComponentUtilities;
import formfiller.utilities.StringUtilities;

public class AddAnswerUseCase extends UndoableUseCaseExecution {
	AddAnswerRequest castRequest;

	//	TODO:	Delete all this?
	/*public void execute(Request request) {
		AddAnswerRequest addAnswerRequest = (AddAnswerRequest) request;
		String questionId = addAnswerRequest.componentId;
		Object content = addAnswerRequest.content;
		//	If the answer content satisfies the answer constraints
		if (content == "") return;
		//		Then add the answer at the Gateway and tell the user.
		AnswerImpl answer = makeAnswer(content);
		
		FormComponent foundComponent = 
				Context.formComponentGateway.find(questionId);
		// TODO:	Get component's AnswerAdditionStrategy from Format/Cardinality.  
		//			Then call add.
		foundComponent.answer = answer;
		// TODO:	Present response.
		
		//	TODO:	Otherwise, inform the user why the answer could not be added.
	}*/

	public void undo() {
		// TODO Implement (After determining RemoveAnswer strategy.)		
	}

	protected void castRequest(Request request) {
		castRequest = (AddAnswerRequest) request;
	}

	protected boolean isRequestMalformed() {
		return StringUtilities.isStringNullOrEmpty(castRequest.componentId) || 
				castRequest.content == null ||
				castRequest.content == "";
	}

	protected void execute() {
		FormComponent component = FormComponentUtilities.find(castRequest.componentId);
		Answer answer = makeAnswer(castRequest.content);
		component.answer = answer;
	}

	protected String makeSuccessfulMessage() {
		return "You added answer, " + castRequest.content;
	}
	
	private Answer makeAnswer(Object content){
		Answer result = new AnswerImpl();
		result.setContent(content);
		return result;
	}
	
}
