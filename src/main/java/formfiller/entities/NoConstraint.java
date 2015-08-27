package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class NoConstraint extends Constraint {

	public NoConstraint() {
		super(ContentConstraint.NONE);
	}

	public boolean isConstraintSatisfied() {
		return answer.satisfiesConstraint();
	}
}
