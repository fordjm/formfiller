package formfiller.boundaries;

import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.ViewModel;

public interface Presenter {	
	void addObserver(ViewModel view);
	
	PresentableResponse getPresentableResponse();
	
	void present(PresentableResponse presentableResponse);	
}
