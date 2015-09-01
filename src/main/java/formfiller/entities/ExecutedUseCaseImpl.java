package formfiller.entities;

import formfiller.boundaries.UseCase;
import formfiller.enums.ActionOutcome;

public class ExecutedUseCaseImpl implements ExecutedUseCase {
	UseCase useCase;
	ActionOutcome outcome;
	String message = "";

	public ExecutedUseCaseImpl(UseCase useCase, ActionOutcome outcome, String message) {
		if (useCase == null) throw new IllegalUseCase();
		if (outcome == null) throw new IllegalOutcome();
		if (message == null) message = "";
		
		this.useCase = useCase;
		this.outcome = outcome;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ActionOutcome getOutcome() {
		return outcome;
	}
	
	public void setOutcome(ActionOutcome outcome) {
		this.outcome = outcome;
	}
	
	public UseCase getUseCase() {
		return useCase;
	}
	
	public class IllegalOutcome extends RuntimeException { }
	
	public class IllegalUseCase extends RuntimeException { }


}
