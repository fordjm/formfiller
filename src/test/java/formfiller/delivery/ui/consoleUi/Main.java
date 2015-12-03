package formfiller.delivery.ui.consoleUi;

import java.util.Scanner;

import formfiller.EventSinks;
import formfiller.delivery.event.impl.EventHandler;
import formfiller.delivery.router.PlaceholderTextRouterFactory;
import formfiller.delivery.router.Router;
import formfiller.utilities.TestSetup;

public class Main {
	private static final Scanner stdIn = new Scanner(System.in);
	private static EventHandler eventHandler;
	
	public static void main(String[] args){
		TestSetup.setupSampleFormComponents();
		EventSinks.add(new ConsoleEventSink());
		setupClassVariables();
		
		handleStartEvent();
		captureEvents();
	}
	
	private static void setupClassVariables() {
		Router router = PlaceholderTextRouterFactory.makeRouter();
		eventHandler = new EventHandler(router);
	}
	
	private static void handleStartEvent() {
		eventHandler.handleEvent("AskQues current");
	}
	
	private static void captureEvents(){
		while (true){
			eventHandler.handleEvent(stdIn.nextLine());
		}
	}
	
}
