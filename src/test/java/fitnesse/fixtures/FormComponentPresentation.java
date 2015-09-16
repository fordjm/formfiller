package fitnesse.fixtures;

import formfiller.FormFillerContext;
import formfiller.appBoundaries.Presenter;
import formfiller.delivery.EventSource;
import formfiller.delivery.event.ConsoleEventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.delivery.router.PlaceholderRouterFactory;
import formfiller.delivery.router.Router;
import formfiller.enums.WhichQuestion;
import formfiller.response.models.PresentableResponse;
import formfiller.usecases.undoable.NullUndoableUseCase;
import formfiller.usecases.undoable.UndoableUseCase;

//	TODO:	Clean and refactor:  Make scenarios, templates, etc.
//			TestSetup should set up variables.  How to access EventHandler?
public class FormComponentPresentation {	
	private EventSource source;
	private Router router;
	private EventHandler handler;

	public FormComponentPresentation() {
		source = new ConsoleEventSource();
		router = PlaceholderRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	
	public void askQuestion(WhichQuestion which) {
		handler.update(source, "AskQuestion " + which.toString());
	}
	
	public void whenTheSystemAsksTheQuestion(WhichQuestion which) {
		askQuestion(which);
	}
	
	//	TODO:	Test whole FormComponent in FitNesse, not just Question.
	//			Also, fix brokenness.
	public String thenTheQuestionMessageIs() {
		PresentableResponse response = getPresentableResponse(
				FormFillerContext.questionPresenter);
		return getErrorMessageIfUseCaseFailed(response.message);
	}

	private PresentableResponse getPresentableResponse(Presenter presenter) {
		return presenter.getPresentableResponse();
	}
	
	//	Temporary hack to get around multi-presenter bug.
	private String getErrorMessageIfUseCaseFailed(String responseMessage){
		if (responseMessage.equals("")){
			return thenTheErrorMessageIs();
		}
		else
			return responseMessage;
	}
	
	public String thenTheAnswerMessageIs() {
		PresentableResponse response = getPresentableResponse(FormFillerContext.
				answerPresenter);
		return response.message;
	}
	
	public String thenTheErrorMessageIs() {
		PresentableResponse response = getPresentableResponse(FormFillerContext.
				responsePresenter);
		return response.message;
	}
	
	public void reset() {
		UndoableUseCase mostRecent = FormFillerContext.executedUseCases.getMostRecent();
		mostRecent.undo();
	}
	
	public void clearFormComponents() {
		FormFillerContext.formComponentGateway.removeAll();
	}
}
