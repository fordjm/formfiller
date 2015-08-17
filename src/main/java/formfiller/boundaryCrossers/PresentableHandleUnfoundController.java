package formfiller.boundaryCrossers;

import formfiller.enums.ActionOutcome;

public class PresentableHandleUnfoundController implements PresentableResponse{
	ActionOutcome outcome;
	String message;
	
	public PresentableHandleUnfoundController(ActionOutcome outcome, String message){
		this.outcome = outcome;
		this.message = message;
	}

	public ActionOutcome getOutcome(){
		return outcome;
	}
	public String getMessage() {
		return message;
	}
}