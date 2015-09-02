package formfiller.deprecated;

import formfiller.entities.Constraint;
import formfiller.enums.ContentConstraint;

public abstract class AddConstraint implements Transaction {
	ContentConstraint constraintName;
	Constraint constraint;
	
	public AddConstraint(ContentConstraint constraint) {
		this.constraintName = constraint;
	}
	
	public void execute() {
		constraint = makeConstraint();
		FormWidget.addConstraint(constraint);
	}
	
	protected abstract Constraint makeConstraint();
}
