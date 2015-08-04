package formfiller.transactions;

import formfiller.entities.Constraint;
import formfiller.entities.ValueMaximum;
import formfiller.enums.ContentConstraint;

public class AddValueMaximum<T> extends AddConstraint<T> {
	T maximum;
	public AddValueMaximum(T maximum) {
		super(ContentConstraint.VALUE_MAXIMUM);
		this.maximum = maximum;
	}
	protected Constraint<T> makeConstraint() {
		return new ValueMaximum<T>(maximum);
	}
}
