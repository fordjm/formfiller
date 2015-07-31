package formfiller.transactions;

import formfiller.entities.ConstraintDecorator;
import formfiller.entities.FreeEntryFormat;
import formfiller.persistence.FormWidget;

public class AddFreeEntryFormat<T> implements Transaction {

	public void execute() {
		ConstraintDecorator<T> format = makeFormat();
		FormWidget.addConstraint(format.getName(), format);
	}

	protected ConstraintDecorator<T> makeFormat() {
		ConstraintDecorator<T> result = new FreeEntryFormat<T>();
		return result;
	}
}