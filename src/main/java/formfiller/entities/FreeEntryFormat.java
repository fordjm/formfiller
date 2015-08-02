package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class FreeEntryFormat<T> extends Constraint<T> {

	public FreeEntryFormat() {
		super(ContentConstraint.FORMAT_FREE_ENTRY);
	}

	@Override
	public boolean isConstraintSatisfied() {
		return response.satisfiesConstraint();
	}
}
