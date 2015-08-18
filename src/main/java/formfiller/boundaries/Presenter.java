package formfiller.boundaries;

import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.View;

public interface Presenter {
	
	void addObserver(View view);
	PresentableResponse getPresentableResponse();
	void present(PresentableResponse presentableResponse);
	
}
