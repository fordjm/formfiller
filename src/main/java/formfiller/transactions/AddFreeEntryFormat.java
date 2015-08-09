package formfiller.transactions;

import formfiller.entities.Constraint;
import formfiller.entities.FreeEntryFormat;
import formfiller.enums.ContentConstraint;

public class AddFreeEntryFormat extends AddConstraint {
	public AddFreeEntryFormat() {
		super(ContentConstraint.FORMAT);
	}
	protected Constraint makeConstraint() {
		return new FreeEntryFormat();
	}
}