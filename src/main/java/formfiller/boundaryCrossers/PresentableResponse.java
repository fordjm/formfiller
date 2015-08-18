package formfiller.boundaryCrossers;

import formfiller.enums.ActionOutcome;

public interface PresentableResponse {
	String getMessage();
	ActionOutcome getOutcome();
	void setMessage(String message);
	void setOutcome(ActionOutcome outcome);
}
