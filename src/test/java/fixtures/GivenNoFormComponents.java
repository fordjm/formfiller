package fixtures;

import formfiller.FormFillerContext;
import formfiller.delivery.EventSource;
import formfiller.delivery.event.ConsoleEventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.delivery.router.PlaceholderRouterFactory;
import formfiller.delivery.router.Router;
import formfiller.enums.WhichQuestion;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.TestSetup;

//	TODO:	Clean and refactor:  Make scenarios, templates, etc.
//			TestSetup should set up variables.  How to access EventHandler?
public class GivenNoFormComponents {	
	private EventSource source;
	private Router router;
	private EventHandler handler;

	public GivenNoFormComponents() {
		TestSetup.setupContext();
		source = new ConsoleEventSource();
		router = PlaceholderRouterFactory.makeRouter();
		handler = new EventHandler(router);
	}
	
	public void whenTheSystemAsksTheQuestion(WhichQuestion which) {
		handler.update(source, "AskQuestion " + which.toString());
	}
	
	//	TODO:	Test whole FormComponent in FitNesse, not just Question.
	public String getPresentableResponseMessage() {
		PresentableResponse response = FormFillerContext.
				questionPresenter.getPresentableResponse();
		return response.message;
	}
}
