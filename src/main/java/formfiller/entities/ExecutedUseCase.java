package formfiller.entities;

import formfiller.appBoundaries.UseCase;
import formfiller.enums.ActionOutcome;

public interface ExecutedUseCase {
	ActionOutcome getOutcome();
	
	void setOutcome(ActionOutcome outcome);
	
	UseCase getUseCase();	
}
