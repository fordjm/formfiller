package formfiller.appBoundaries;

import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableResponse;

/**
 * Presenter is the input boundary interface to the application partition.
 */
public interface Presenter {		
	void clearPresentableResponse();
	
	ViewModel getViewModel();
	
	void present(PresentableResponse presentableResponse);	
}
