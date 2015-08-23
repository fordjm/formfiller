package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMinimum<T> extends Constraint {
	T minimum;

	public ValueMinimum(T minimum) {
		super(ContentConstraint.VALUE_MINIMUM);
		this.minimum = minimum;
	}

	@Override
	protected boolean isConstraintSatisfied() {
		return isLegalValue() && answer.satisfiesConstraint();
	}
	
	protected boolean isLegalValue(){
		T content = answer.getContent();
		return isComparable(content) && isGreaterOrEqualToMinimum(content);
	}
	
	protected boolean isComparable(T content){
		return content instanceof Comparable;
	}
	
	protected boolean isGreaterOrEqualToMinimum(T content){
		Comparable<T> castContent = (Comparable<T>) content;
		return castContent.compareTo(minimum) >= 0;
	}
}
