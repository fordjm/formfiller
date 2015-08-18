package formfiller.boundaryCrossers;

import formfiller.enums.ActionOutcome;

public class PresentableNavigation implements PresentableResponse {
	String message = "";
	ActionOutcome outcome;

	public PresentableNavigation() { }

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
		if (outcome == null) throw new IllegalOutcome();
		this.outcome = outcome;
	}
	
	public class IllegalOutcome extends RuntimeException { }

}
