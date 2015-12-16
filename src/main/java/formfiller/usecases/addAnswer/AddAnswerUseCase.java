package formfiller.usecases.addAnswer;

import formfiller.Context;
import formfiller.EventSinks;
import formfiller.entities.Answer;
import formfiller.entities.AnswerImpl;
import formfiller.entities.formComponent.FormComponent;
import formfiller.request.models.AddAnswerRequest;
import formfiller.request.models.Request;
import formfiller.usecases.undoable.UndoableUseCase;
import formfiller.utilities.StringUtilities;

//	TODO:	Check request for null or empty strings.  Throw exception if found.
public class AddAnswerUseCase implements UndoableUseCase {
	private AddAnswerRequest castRequest;
	private FormComponent foundComponent;

	public void execute(Request request) {
		castRequest(request);
		foundComponent = getFormComponent();
		updateFormComponent();
		AddAnswerResponseModel responseModel = makeResponseModel();
		AddAnswerPresenter presenter = new AddAnswerPresenter();
		presenter.present(responseModel);
		EventSinks.receive(presenter.getViewModel());
	}

	protected void castRequest(Request request) {
		castRequest = (AddAnswerRequest) request;
	}

	private FormComponent getFormComponent() {
		return Context.formComponentGateway.find(castRequest.componentId);
	}

	private void updateFormComponent() {
		Answer answer = makeAnswer();
		if (!foundComponent.validator.accepts(answer))
			throw new IllegalArgumentException(makeAnswerRejectedMessage());
		// TODO:	Get component's AnswerAdditionStrategy from Format/Cardinality, then call add.
		foundComponent.answer = answer;
	}

	private Answer makeAnswer(){
		Answer result = new AnswerImpl();
		result.setId(castRequest.componentId);
		result.setContent(castRequest.content);
		return result;
	}
	
	private String makeAnswerRejectedMessage() {
		//	TODO:	More expressive message or multiple exceptions.
		return "The question " + castRequest.componentId + "does not accept the answer " + 
				castRequest.content.toString();
	}

	private AddAnswerResponseModel makeResponseModel() {
		AddAnswerResponseModel result = new AddAnswerResponseModel();
		result.id = castRequest.componentId;
		result.format = foundComponent.format.getName();
		result.message = makeSuccessfulMessage();
		result.content = castRequest.content;
		return result;
	}

	public void undo() {
		// TODO Implement (After determining RemoveAnswer strategy.)		
	}

	protected String makeSuccessfulMessage() {
		return "You added the answer, " + castRequest.content;
	}
	
}
