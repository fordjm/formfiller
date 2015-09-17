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
import formfiller.usecases.undoable.UndoableUseCase;

//	TODO:	TestSetup should set up variables.  How to access EventHandler?
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
	
	public String thenTheMessageIs(String chooseMessage) {
		Presenter presenter = choosePresenter(chooseMessage);
		PresentableResponse response = getPresentableResponse(
				presenter);
		return response.message;
	}
	
	private Presenter choosePresenter(String chooseMessage) {
		if (chooseMessage.equalsIgnoreCase("question"))
			return FormFillerContext.questionPresenter;
		else if (chooseMessage.equalsIgnoreCase("answer"))
			return FormFillerContext.answerPresenter;
		else if (chooseMessage.equalsIgnoreCase("error"))
			return FormFillerContext.responsePresenter;
		throw new IllegalArgumentException();	//	TODO:	Customize
	}

	private PresentableResponse getPresentableResponse(Presenter presenter) {
		return presenter.getPresentableResponse();
	}
	
	public void reset() {
		UndoableUseCase mostRecent = FormFillerContext.executedUseCases.getMostRecent();
		mostRecent.undo();
	}
	
	public void clearFormComponents() {
		FormFillerContext.formComponentGateway.removeAll();
	}
}
