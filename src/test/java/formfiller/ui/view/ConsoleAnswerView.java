package formfiller.ui.view;
import java.util.Scanner;

public class ConsoleAnswerView implements UserRequestSource {
	Scanner stdIn = new Scanner(System.in);
	
	// Should almost definitely be in ViewModel.
	public String getUserRequestString() {
		return stdIn.nextLine();
	}

}
