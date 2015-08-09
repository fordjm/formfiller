package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class FreeEntryFormat extends Constraint {

	public FreeEntryFormat() {
		super(ContentConstraint.FORMAT);
	}

	@Override
	public boolean isConstraintSatisfied() {
		return response.satisfiesConstraint();
	}
}
