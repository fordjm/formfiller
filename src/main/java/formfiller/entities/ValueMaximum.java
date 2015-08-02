package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMaximum<T> extends Constraint<T> {
	T maximum;

	public ValueMaximum(T maximum) {
		super(ContentConstraint.VALUE_MAXIMUM);
		this.maximum = maximum;
	}

	@Override
	protected boolean isConstraintSatisfied() {
		return isLegalValue() && response.satisfiesConstraint();
	}
	
	protected boolean isLegalValue(){
		T content = response.getContent();
		return isComparable(content) && isLessOrEqualToMaximum(content);
	}
	
	protected boolean isComparable(T content){
		return content instanceof Comparable;
	}
	
	protected boolean isLessOrEqualToMaximum(T content){
		Comparable<T> castContent = (Comparable<T>) content;
		return castContent.compareTo(maximum) <= 0;
	}
}
