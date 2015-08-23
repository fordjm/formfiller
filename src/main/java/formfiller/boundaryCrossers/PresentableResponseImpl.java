package formfiller.boundaryCrossers;

import formfiller.enums.ActionOutcome;

public class PresentableResponseImpl implements PresentableResponse {
	String message = "";
	ActionOutcome outcome = ActionOutcome.NO_OUTCOME;

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
