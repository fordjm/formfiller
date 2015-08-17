package formfiller.boundaryCrossers;

import formfiller.enums.ActionOutcome;

public class PresentableQuestion implements PresentableResponse {
	
	public String id;
	public String message;
	public ActionOutcome outcome;
	
	public PresentableQuestion() { }

	public String getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public ActionOutcome getOutcome() {
		return outcome;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setOutcome(ActionOutcome outcome) {
		this.outcome = outcome;
	}
	
}