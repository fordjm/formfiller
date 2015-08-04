package formfiller.transactions;

import formfiller.entities.Constraint;
import formfiller.enums.ContentConstraint;
import formfiller.persistence.FormWidget;

public abstract class AddConstraint<T> implements Transaction {
	ContentConstraint constraintName;
	Constraint<T> constraint;
	public AddConstraint(ContentConstraint constraint) {
		this.constraintName = constraint;
	}
	public void execute() {
		constraint = makeConstraint();
		FormWidget.addConstraint(constraint);
	}
	protected abstract Constraint<T> makeConstraint();
}
