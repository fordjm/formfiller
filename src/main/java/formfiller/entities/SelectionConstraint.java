package formfiller.entities;

import java.util.List;

import formfiller.enums.ContentConstraint;

public class SelectionConstraint<T> extends Constraint {
	private List<T> selections;
	
	public SelectionConstraint(List<T> selections) {
		super(ContentConstraint.SELECTION);
		this.selections = selections;
	}
	
	protected boolean isConstraintSatisfied() {
		return selections.size() > 0 && 
				selections.contains(answer.getContent()) && 
				answer.satisfiesConstraint();
	}

	public List<T> getSelections() {
		return selections;
	}
}
