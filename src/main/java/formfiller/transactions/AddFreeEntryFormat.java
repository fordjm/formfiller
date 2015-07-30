package formfiller.transactions;

import formfiller.entities.AbstractResponse;
import formfiller.entities.ConstraintDecorator;
import formfiller.entities.FreeEntryFormat;
import formfiller.persistence.FormWidget;

public class AddFreeEntryFormat<T> implements Transaction {
	AbstractResponse<T> response;
	
	public AddFreeEntryFormat(AbstractResponse<T> response){
		this.response = response;
	}

	public void execute() {
		ConstraintDecorator<T> format = makeFormat();
		FormWidget.addConstraint(format.getName(), format);
	}

	protected ConstraintDecorator<T> makeFormat() {
		ConstraintDecorator<T> result = new FreeEntryFormat<T>();
		return result;
	}
}