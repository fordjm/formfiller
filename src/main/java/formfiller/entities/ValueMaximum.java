package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMaximum<T> extends Constraint {
	Object maximum;

	public ValueMaximum(Object maximum) {
		super(ContentConstraint.VALUE_MAXIMUM);
		this.maximum = maximum;
	}

	@Override
	protected boolean isConstraintSatisfied() {
		return isLegalValue() && answer.satisfiesConstraint();
	}
	
	protected boolean isLegalValue(){
		Object content = answer.getContent();
		return isComparable(content) && isLessOrEqualToMaximum(content);
	}
	
	protected boolean isComparable(Object content){
		return content instanceof Comparable;
	}
	
	protected boolean isLessOrEqualToMaximum(Object content){
		Comparable<Object> castContent = (Comparable<Object>) content;
		return castContent.compareTo(maximum) <= 0;
	}
}
