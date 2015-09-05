package formfiller.delivery.ui.consoleUi;

import formfiller.delivery.EventSource;
import formfiller.delivery.event.EventHandler;
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
		eventHandler.update(eventSource, "navigation none");
	}
	
	private static void setupClassVariables() {
		eventHandler = new EventHandler();
		eventSource = new ConsoleEventSource();
		eventSource.addObserver(eventHandler);
	}
}
