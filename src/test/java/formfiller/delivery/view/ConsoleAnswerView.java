package formfiller.delivery.view;
import java.util.Scanner;

import formfiller.delivery.UserRequestSource;

public class ConsoleAnswerView implements UserRequestSource {
	Scanner stdIn = new Scanner(System.in);
	
	// Should almost definitely be in ViewModel.
	public String getUserRequestString() {
		return stdIn.nextLine();
	}

}
