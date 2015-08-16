package formfiller.ui.view;

import java.util.Observable;
import java.util.Scanner;

import formfiller.ApplicationContext;
import formfiller.usecases.navigation.PresentableNavigation;

public class ConsoleNavigationView implements NavigationView {

	public void update(Observable observable, Object input) {
		displayNavigation();
	}
	public void displayNavigation() {
		PresentableNavigation presentableNavigation = 
				ApplicationContext.navigationResponseBoundary.getPresentableNavigation();
		if (presentableNavigation.failed()){
			String errorMessage = presentableNavigation.getMessage();
			outputSystemMessage(errorMessage);

			respondToNavigationError(presentableNavigation);
		}
	}
	private void outputSystemMessage(String systemMessage) {
		System.out.println(systemMessage);
	}
	
	// TODO:  	Get this code out of here!
	//			Make the Scanner a singleton.
	//			Move "repeat" to a VUI class.  Doesn't make sense in console UI.
	private void respondToNavigationError(PresentableNavigation presentableNavigation){
		Scanner stdIn = new Scanner(System.in);
		boolean readyToContinue = false;
		
		while (!readyToContinue) {
			String responseToError = "";
			outputSystemMessage("Please input 'repeat' to repeat the error message or "
					+ "'continue' to continue.");
			responseToError = stdIn.nextLine();
			if (responseToError.equalsIgnoreCase("continue"))
				break;
			else if (responseToError.equalsIgnoreCase("repeat"))
				outputSystemMessage(presentableNavigation.getMessage());
			else
				outputSystemMessage("I'm sorry, I didn't understand.");
		}
	}
}
