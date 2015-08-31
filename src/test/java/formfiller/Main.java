package formfiller;

import formfiller.delivery.EventParser;
import formfiller.delivery.EventSource;
import formfiller.delivery.controller.NavigationController;
import formfiller.delivery.eventParser.ConsoleEventParser;
import formfiller.delivery.eventParser.ParsedEvent;
import formfiller.delivery.router.Router;
import formfiller.delivery.view.PresentAnswerViewModel;
import formfiller.delivery.view.HandleUnfoundControllerViewModel;
import formfiller.delivery.view.NavigationViewModel;
import formfiller.delivery.view.PresentQuestionViewModel;
import formfiller.utilities.TestSetup;

public class Main {
	private static EventSource eventSource;
	private static EventParser eventParser;
	private static Router router;
	
	public static void main(String[] args){
		TestSetup.setupSampleFormComponents();
		setupClassVariables();
		
		outputCheapHackyStartPrompt();
		while (true){
			routeEvents(router);
		}
	}
	
	private static void outputCheapHackyStartPrompt() {
		System.out.print("Please enter the name of an event to handle:  ");
	}
	
	private static void setupClassVariables() {
		ApplicationContext.handleUnfoundControllerPresenter.addObserver(
				new HandleUnfoundControllerViewModel());
		ApplicationContext.navigationPresenter.addObserver(new NavigationViewModel());
		ApplicationContext.questionPresenter.addObserver(new PresentQuestionViewModel());
		ApplicationContext.answerPresenter.addObserver(new PresentAnswerViewModel());
		
		eventSource = new ConsoleEventSource();
		eventParser = new ConsoleEventParser();
		router = makeRouter();
	}
	
	private static Router makeRouter(){
		Router result = new Router();
		result.addMethod("navigation", new NavigationController());
		return result;
	}
	
	private static void routeEvents(Router router) {
		String event = eventSource.getInputEvent();
		ParsedEvent parsedEvent = eventParser.parse(event);
		router.route(parsedEvent);
	}	
}
