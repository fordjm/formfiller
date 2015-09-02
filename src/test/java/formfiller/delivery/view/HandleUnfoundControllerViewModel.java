package formfiller.delivery.view;

import java.util.Observable;

import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableResponse;

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
