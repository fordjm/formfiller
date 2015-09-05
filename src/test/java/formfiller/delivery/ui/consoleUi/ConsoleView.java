package formfiller.delivery.ui.consoleUi;

import java.util.Observable;

import formfiller.delivery.View;
import formfiller.response.models.PresentableResponse;

public class ConsoleView implements View {
	public boolean outputRan;

	public void output(String message){
		if (message == null) message = "";
		
		outputRan = true;
		if (message.length() > 0) 
			System.out.println(message);
	}

	public void update(Observable o, Object input) {
		outputRan = false;
		PresentableResponse response = (PresentableResponse) input;
		output(response.message);
	}
}
