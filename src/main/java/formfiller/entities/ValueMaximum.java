package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMaximum implements Constrainable {
	ContentConstraint name = ContentConstraint.VALUE_MAXIMUM;
	Object maximum;
	Object constrainedValue;

	public ValueMaximum(Object maximum) {
		this.maximum = maximum;
	}
	
	public Constrainable constrain(Object value){
		this.constrainedValue = value;
		return this;
	}

	public boolean isSatisfied() {
		return isComparable() && isLessThanOrEqualToMaximum();
	}
	
	protected boolean isComparable(){
		return constrainedValue instanceof Comparable;
	}
	
	protected boolean isLessThanOrEqualToMaximum(){
		Comparable<Object> castValue = (Comparable<Object>) constrainedValue;
		return castValue.compareTo(maximum) <= 0;
	}
}
