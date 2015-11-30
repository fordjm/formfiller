package formfiller.delivery.ui.consoleUi;

import formfiller.delivery.event.EventSource;
import formfiller.delivery.event.impl.ConsoleEventSource;
import formfiller.delivery.event.impl.EventHandler;
import formfiller.delivery.router.PlaceholderTextRouterFactory;
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
		eventHandler.update(eventSource, "AskQues current");
	}
	
	private static void setupClassVariables() {
		Router router = PlaceholderTextRouterFactory.makeRouter();
		eventHandler = new EventHandler(router);
		eventSource = new ConsoleEventSource();
		eventSource.addObserver(eventHandler);
	}
}
