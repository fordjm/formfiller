package formfiller.entities;

import formfiller.boundaries.UseCase;
import formfiller.enums.ActionOutcome;

public interface ExecutedUseCase {
	ActionOutcome getOutcome();
	
	void setOutcome(ActionOutcome outcome);
	
	UseCase getUseCase();	
}
