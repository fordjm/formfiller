package formfiller.transactions;

import formfiller.entities.Constraint;
import formfiller.entities.ValueMinimum;
import formfiller.enums.ContentConstraint;

public class AddValueMinimum<T> extends AddConstraint {
	T minimum;
	public AddValueMinimum(T minimum) {
		super(ContentConstraint.VALUE_MINIMUM);
		this.minimum = minimum;
	}
	protected Constraint makeConstraint() {
		return new ValueMinimum<T>(minimum);
	}
}
