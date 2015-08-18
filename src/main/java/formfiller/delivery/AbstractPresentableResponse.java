package formfiller.delivery;

import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.enums.ActionOutcome;

public class AbstractPresentableResponse implements PresentableResponse {
	String message = "";
	ActionOutcome outcome;

	public String getMessage() {
		return message;
	}
	public ActionOutcome getOutcome() {
		return outcome;
	}

}
