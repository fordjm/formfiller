package formfiller.delivery.viewModel;

import java.util.Observable;

import formfiller.delivery.ViewModel;
import formfiller.delivery.ui.testConsoleUi.ConsoleView;
import formfiller.response.models.PresentableResponse;

//	TODO:	This could probably be a Singleton.
public class FailedUseCaseViewModel implements ViewModel {

	public void update(Observable o, Object input) {
		outputPresentableResponse(input);
	}
	
	public void outputPresentableResponse(Object input) {
		PresentableResponse presentableFailedUseCase = 
				(PresentableResponse) input;
		ConsoleView.output(presentableFailedUseCase.message);
	}
}
