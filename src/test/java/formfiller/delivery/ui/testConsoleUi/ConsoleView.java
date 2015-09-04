package formfiller.delivery.ui.testConsoleUi;

import java.util.Observable;

import formfiller.delivery.View;
import formfiller.response.models.PresentableResponse;

public class ConsoleView implements View {
	public boolean outputRan;

	public void output(String message){
		outputRan = true;
		System.out.println(message);
	}

	public void update(Observable o, Object input) {
		outputRan = false;
		PresentableResponse response = (PresentableResponse) input;
		output(response.message);
	}
}
