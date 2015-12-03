package formfiller.delivery.ui.consoleUi;

import java.util.Observable;

import formfiller.response.models.PresentableResponse;

public class ConsoleView {
	public boolean outputRan;

	public void generateView(String message){
		if (message == null) message = "";
		
		outputRan = true;
		if (message.length() > 0) 
			System.out.println(message);
	}
	
	//	TODO:	ViewableMessage has a String content	
	//			ViewableFormComponent has (Viewable components)
	//			ViewableQuestion and ViewableAnswer have a String content
	//			Where are options?  (Presentation depends on Format.)

	public void update(Observable o, Object input) {
		outputRan = false;
		PresentableResponse response = (PresentableResponse) input;
		generateView(response.message);
	}
	
}
