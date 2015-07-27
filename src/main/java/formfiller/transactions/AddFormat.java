package formfiller.transactions;

import formfiller.entities.ResponseFormat;
import formfiller.persistence.FormWidget;
import formfiller.utilities.ConstraintName;

public abstract class AddFormat<T> implements Transaction {

	public void execute() {
		FormWidget.addConstraint(
				ConstraintName.FORMAT, makeFormat());
	}

	protected abstract ResponseFormat<T> makeFormat();
}
