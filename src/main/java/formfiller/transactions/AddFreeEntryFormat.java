package formfiller.transactions;

import formfiller.entities.Constraint;
import formfiller.entities.FreeEntryFormat;
import formfiller.enums.ContentConstraint;

public class AddFreeEntryFormat<T> extends AddConstraint<T> {
	public AddFreeEntryFormat() {
		super(ContentConstraint.FORMAT);
	}
	protected Constraint<T> makeConstraint() {
		return new FreeEntryFormat<T>();
	}
}