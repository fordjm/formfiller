package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMinimum implements Constrainable {
	ContentConstraint name = ContentConstraint.VALUE_MINIMUM;
	Object minimum;
	Object constrainedValue;

	public ValueMinimum(Object minimum) {
		this.minimum = minimum;
	}

	public boolean isSatisfiedBy(Object candidate) {
		return isComparable(candidate) && isGreaterOrEqualToMinimum(candidate);
	}
	
	private boolean isComparable(Object candidate){
		return candidate instanceof Comparable;
	}
	
	private boolean isGreaterOrEqualToMinimum(Object candidate){
		Comparable<Object> castValue = (Comparable<Object>) candidate;
		return castValue.compareTo(minimum) >= 0;
	}
}
