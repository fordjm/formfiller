package formfiller.delivery.ui.testConsoleUi;

import formfiller.ApplicationContext;
import formfiller.delivery.EventSource;
import formfiller.delivery.event.EventHandler;
import formfiller.delivery.viewModel.HandleUnfoundControllerViewModel;
import formfiller.delivery.viewModel.NavigationViewModel;
import formfiller.delivery.viewModel.AnswerViewModel;
import formfiller.delivery.viewModel.QuestionViewModel;
import formfiller.utilities.TestSetup;

public class Main {
	private static EventHandler eventHandler;
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
		ApplicationContext.questionPresenter.addObserver(new QuestionViewModel());
		ApplicationContext.answerPresenter.addObserver(new AnswerViewModel());
	}
	
	private static void setupClassVariables() {
		eventHandler = new EventHandler();
		eventSource = new ConsoleEventSource();
		eventSource.addObserver(eventHandler);
	}
}
