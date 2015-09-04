package formfiller.appBoundaries;

import formfiller.response.models.PresentableResponse;

public interface Presenter {		
	PresentableResponse getPresentableResponse();
	
	void present(PresentableResponse presentableResponse);	
}
