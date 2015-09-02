package formfiller.deprecated;

import java.util.List;

import formfiller.entities.Constraint;
import formfiller.entities.SelectionConstraint;
import formfiller.enums.ContentConstraint;

public class AddSelectionConstraint<T> extends AddConstraint {
	private List<T> selections;

	public AddSelectionConstraint(List<T> selections) {
		super(ContentConstraint.SELECTION);
		this.selections = selections;
	}

	protected Constraint makeConstraint() {
		return new SelectionConstraint<T>(selections);
	}
}
