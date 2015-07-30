package formfiller.entities;

import formfiller.utilities.ConstraintName;

public class FreeEntryFormat<T> extends ConstraintDecorator<T> {

	public FreeEntryFormat() {
		super(ConstraintName.FORMAT_FREE_ENTRY);
	}

	@Override
	public boolean isConstraintSatisfied() {
		return response.satisfiesConstraint();
	}
}
