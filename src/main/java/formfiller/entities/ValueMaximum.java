package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMaximum implements Constrainable {
	ContentConstraint name = ContentConstraint.VALUE_MAXIMUM;
	Object maximum;
	Object constrainedValue;

	public ValueMaximum(Object maximum) {
		this.maximum = maximum;
	}

	public boolean isSatisfiedBy(Object candidate) {
		return isComparable(candidate) && isLessThanOrEqualToMaximum(candidate);
	}
	
	protected boolean isComparable(Object candidate){
		return candidate instanceof Comparable;
	}
	
	protected boolean isLessThanOrEqualToMaximum(Object candidate){
		Comparable<Object> castValue = (Comparable<Object>) candidate;
		return castValue.compareTo(maximum) <= 0;
	}
}
