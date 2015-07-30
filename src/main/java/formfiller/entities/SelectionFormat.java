package formfiller.entities;

import java.util.List;

import formfiller.utilities.ConstraintName;

public class SelectionFormat<T> extends ConstraintDecorator<T> {
	private List<T> selections;
	
	public SelectionFormat(List<T> selections) throws IllegalArgumentException {
		super(ConstraintName.FORMAT_SELECTION);
		this.selections = selections;
	}
	
	@Override
	protected boolean isConstraintSatisfied() {
		return selections.size() > 0 && selections.contains(response.getContent()) && 
				response.satisfiesConstraint();
	}

	public List<T> getSelections() {
		return selections;
	}
}
