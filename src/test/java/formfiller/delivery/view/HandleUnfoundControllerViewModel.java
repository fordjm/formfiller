package formfiller.delivery.view;

import java.util.Observable;

import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.ViewModel;

public class HandleUnfoundControllerViewModel implements ViewModel {

	public void update(Observable o, Object input) {
		outputPresentableResponse(input);
	}
	
	public void outputPresentableResponse(Object input) {
		PresentableResponse presentableHandleUnfoundController = 
				(PresentableResponse) input;
		ConsoleView.output(presentableHandleUnfoundController.message);
	}
}
