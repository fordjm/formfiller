package formfiller;

import formfiller.delivery.EventSource;
import formfiller.delivery.eventParser.StringEventHandler;
import formfiller.delivery.view.HandleUnfoundControllerViewModel;
import formfiller.delivery.view.NavigationViewModel;
import formfiller.delivery.view.PresentAnswerViewModel;
import formfiller.delivery.view.PresentQuestionViewModel;
import formfiller.utilities.TestSetup;

public class Main {
	private static StringEventHandler eventHandler;
	private static EventSource eventSource;
	
	public static void main(String[] args){
		TestSetup.setupSampleFormComponents();
		addObserversToPresenters();
		setupClassVariables();
		
		outputCheapHackyStartPrompt();
		eventSource.captureEvents();
	}
	
	private static void outputCheapHackyStartPrompt() {
		System.out.print("Please enter the name of an event to handle:  ");
	}

	private static void addObserversToPresenters() {
		ApplicationContext.handleUnfoundControllerPresenter.addObserver(
				new HandleUnfoundControllerViewModel());
		ApplicationContext.navigationPresenter.addObserver(new NavigationViewModel());
		ApplicationContext.questionPresenter.addObserver(new PresentQuestionViewModel());
		ApplicationContext.answerPresenter.addObserver(new PresentAnswerViewModel());
	}
	
	private static void setupClassVariables() {
		eventHandler = new StringEventHandler();
		eventSource = new ConsoleEventSource();
		eventSource.addObserver(eventHandler);
	}
}
