package formfiller.entities;

import java.util.List;

import formfiller.enums.ContentConstraint;

public class SelectionFormat<T> extends Constraint<T> {
	private List<T> selections;
	
	public SelectionFormat(List<T> selections) throws IllegalArgumentException {
		super(ContentConstraint.FORMAT);
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
