package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMinimum implements Constrainable {
	ContentConstraint name = ContentConstraint.VALUE_MINIMUM;
	Object minimum;
	Object constrainedValue;

	public ValueMinimum(Object minimum) {
		this.minimum = minimum;
	}

	public Constrainable constrain(Object value) {
		constrainedValue = value;
		return this;
	}

	public boolean isSatisfied() {
		return isComparable() && isGreaterOrEqualToMinimum();
	}
	
	private boolean isComparable(){
		return constrainedValue instanceof Comparable;
	}
	
	private boolean isGreaterOrEqualToMinimum(){
		Comparable<Object> castValue = (Comparable<Object>) constrainedValue;
		return castValue.compareTo(minimum) >= 0;
	}
}
