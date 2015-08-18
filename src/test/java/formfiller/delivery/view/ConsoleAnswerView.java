package formfiller.delivery.view;
import java.util.Observable;
import java.util.Scanner;

import formfiller.delivery.UserRequestSource;

public class ConsoleAnswerView implements UserRequestSource {
	Scanner stdIn = new Scanner(System.in);
	
	// Should almost definitely be in ViewModel.
	public String getUserRequestString() {
		return stdIn.nextLine();
	}
	public void update(Observable o, Object arg) {
		displayPresentableResponse();
	}
	public void displayPresentableResponse() { }

}