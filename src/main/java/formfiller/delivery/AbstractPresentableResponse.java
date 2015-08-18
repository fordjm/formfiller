package formfiller.delivery;

import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.enums.ActionOutcome;

// TODO:	Abstract how, exactly?
public class AbstractPresentableResponse implements PresentableResponse {
	String message = "";
	ActionOutcome outcome;

	public String getMessage() {
		return message;
	}
	public ActionOutcome getOutcome() {
		return outcome;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setOutcome(ActionOutcome outcome) {
		this.outcome = outcome;
	}

}
