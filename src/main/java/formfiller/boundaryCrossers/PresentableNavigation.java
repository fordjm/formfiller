package formfiller.boundaryCrossers;

import formfiller.enums.ActionOutcome;

public class PresentableNavigation implements PresentableResponse {
	String message;
	ActionOutcome outcome;
	
	public PresentableNavigation(String message, ActionOutcome outcome){
		this.message = message;
		this.outcome = outcome;
	}

	public String getMessage() {
		return message;
	}
	public ActionOutcome getOutcome() {
		return outcome;
	}

}
