package formfiller.transactions;

import java.util.List;

import formfiller.entities.Constraint;
import formfiller.entities.SelectionFormat;
import formfiller.persistence.FormWidget;

public class AddSelectionFormat<T> implements Transaction {
	private List<T> selections;

	public AddSelectionFormat(List<T> selections) {
		this.selections = selections;
	}

	public void execute() {
		Constraint<T> format = makeFormat();
		FormWidget.addConstraint(format.getName(), format);
	}

	protected Constraint<T> makeFormat() {
		Constraint<T> result = new SelectionFormat<T>(selections);
		return result;
	}
}
