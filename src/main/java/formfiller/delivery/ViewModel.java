package formfiller.delivery;

import java.util.Observer;

public interface ViewModel extends Observer {
	void outputPresentableResponse(Object input);
}
