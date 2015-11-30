package formfiller.appBoundaries;

import formfiller.delivery.ViewModel;
import formfiller.response.models.PresentableResponse;

public interface Presenter {		
	void clearPresentableResponse();
	
	ViewModel getViewModel();
	
	void present(PresentableResponse presentableResponse);	
}
