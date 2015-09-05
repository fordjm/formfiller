package formfiller.delivery.viewModel;

import java.util.Observable;

import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableResponse;

public class PresentableResponseViewModel extends Observable implements ViewModel {
	
	public void outputPresentableResponse(Object input) {
		PresentableResponse presentableResponse = 
				(PresentableResponse) input;
		setChanged();
		notifyObservers(presentableResponse);
	}
}
