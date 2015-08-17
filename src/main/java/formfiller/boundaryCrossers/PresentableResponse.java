package formfiller.boundaryCrossers;

import formfiller.enums.ActionOutcome;

public interface PresentableResponse {
	String getMessage();
	ActionOutcome getOutcome();
}
