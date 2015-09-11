package formfiller.delivery.ui.consoleUi;

import formfiller.delivery.EventSource;
import formfiller.delivery.event.ConsoleEventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.delivery.router.PlaceholderRouterFactory;
import formfiller.delivery.router.Router;
import formfiller.utilities.TestSetup;

public class Main {
	private static EventHandler eventHandler;
	private static EventSource eventSource;
	
	public static void main(String[] args){
		TestSetup.setupSampleFormComponents();
		setupClassVariables();
		
		handleStartEvent();
		eventSource.captureEvents();
	}
	
	private static void handleStartEvent() {
		eventHandler.update(eventSource, "AskQuestion current");
	}
	
	private static void setupClassVariables() {
		Router router = PlaceholderRouterFactory.makeRouter();
		eventHandler = new EventHandler(router);
		eventSource = new ConsoleEventSource();
		eventSource.addObserver(eventHandler);
	}
}
