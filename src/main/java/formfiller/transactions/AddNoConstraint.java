package formfiller.transactions;

import formfiller.entities.Constraint;
import formfiller.entities.NoConstraint;
import formfiller.enums.ContentConstraint;

public class AddNoConstraint extends AddConstraint {
	
	public AddNoConstraint() {
		super(ContentConstraint.NONE);
	}
	
	protected Constraint makeConstraint() {
		return new NoConstraint();
	}
}