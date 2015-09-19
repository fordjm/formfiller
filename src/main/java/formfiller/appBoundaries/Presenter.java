package formfiller.appBoundaries;

import formfiller.response.models.PresentableResponse;

public interface Presenter {		
	void clearPresentableResponse();
	
	PresentableResponse getPresentableResponse();
	
	void present(PresentableResponse presentableResponse);	
}
