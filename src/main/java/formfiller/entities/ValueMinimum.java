package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMinimum<T> extends Constraint<T> {
	T minimum;

	public ValueMinimum(T minimum) {
		super(ContentConstraint.VALUE_MINIMUM);
		this.minimum = minimum;
	}

	@Override
	protected boolean isConstraintSatisfied() {
		return isLegalValue() && response.satisfiesConstraint();
	}
	
	protected boolean isLegalValue(){
		T content = response.getContent();
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
