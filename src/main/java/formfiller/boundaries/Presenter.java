package formfiller.boundaries;

import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableResponse;

public interface Presenter {	
	void addObserver(ViewModel view);
	
	PresentableResponse getPresentableResponse();
	
	void present(PresentableResponse presentableResponse);	
}
