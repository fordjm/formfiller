package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMinimum<T> extends Constraint {
	Object minimum;

	public ValueMinimum(Object minimum) {
		super(ContentConstraint.VALUE_MINIMUM);
		this.minimum = minimum;
	}

	protected boolean isConstraintSatisfied() {
		return isLegalValue() && answer.satisfiesConstraint();
	}
	
	protected boolean isLegalValue(){
		Object content = answer.getContent();
		return isComparable(content) && isGreaterOrEqualToMinimum(content);
	}
	
	protected boolean isComparable(Object content){
		return content instanceof Comparable;
	}
	
	protected boolean isGreaterOrEqualToMinimum(Object content){
		Comparable<Object> castContent = (Comparable<Object>) content;
		return castContent.compareTo(minimum) >= 0;
	}
}
