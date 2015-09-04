package formfiller.delivery.ui.testConsoleUi;

import formfiller.delivery.EventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.utilities.TestSetup;

public class Main {
	private static EventHandler eventHandler;
	private static EventSource eventSource;
	
	public static void main(String[] args){
		TestSetup.setupSampleFormComponents();
		setupClassVariables();
		
		outputCheapHackyStartPrompt();
		eventSource.captureEvents();
	}
	
	private static void outputCheapHackyStartPrompt() {
		System.out.print("Please enter the name of an event to handle:  ");
	}
	
	private static void setupClassVariables() {
		eventHandler = new EventHandler();
		eventSource = new ConsoleEventSource();
		eventSource.addObserver(eventHandler);
	}
}
