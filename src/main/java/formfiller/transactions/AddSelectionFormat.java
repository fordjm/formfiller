package formfiller.transactions;

import java.util.List;

import formfiller.entities.ConstraintDecorator;
import formfiller.entities.SelectionFormat;
import formfiller.persistence.FormWidget;

public class AddSelectionFormat<T> implements Transaction {
	private List<T> selections;

	public AddSelectionFormat(List<T> selections) {
		this.selections = selections;
	}

	public void execute() {
		ConstraintDecorator<T> format = makeFormat();
		FormWidget.addConstraint(format.getName(), format);
	}

	protected ConstraintDecorator<T> makeFormat() {
		ConstraintDecorator<T> result = new SelectionFormat<T>(selections);
		return result;
	}
}
