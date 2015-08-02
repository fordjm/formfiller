package formfiller.transactions;

import formfiller.entities.Constraint;
import formfiller.entities.FreeEntryFormat;
import formfiller.persistence.FormWidget;

public class AddFreeEntryFormat<T> implements Transaction {

	public void execute() {
		Constraint<T> format = makeFormat();
		FormWidget.addConstraint(format.getName(), format);
	}

	protected Constraint<T> makeFormat() {
		Constraint<T> result = new FreeEntryFormat<T>();
		return result;
	}
}