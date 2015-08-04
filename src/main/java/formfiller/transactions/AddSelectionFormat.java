package formfiller.transactions;

import java.util.List;

import formfiller.entities.Constraint;
import formfiller.entities.SelectionFormat;
import formfiller.enums.ContentConstraint;

public class AddSelectionFormat<T> extends AddConstraint<T> {
	private List<T> selections;

	public AddSelectionFormat(List<T> selections) {
		super(ContentConstraint.FORMAT);
		this.selections = selections;
	}

	protected Constraint<T> makeConstraint() {
		return new SelectionFormat<T>(selections);
	}
}
