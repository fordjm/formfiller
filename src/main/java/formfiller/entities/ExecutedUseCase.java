package formfiller.entities;

import formfiller.appBoundaries.UseCase;
import formfiller.enums.ActionOutcome;

//	TODO:	This is not an entity.  Move it.
public class ExecutedUseCase {
	public UseCase useCase;
	public ActionOutcome outcome;
	public String message = "";
}
