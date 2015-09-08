package formfiller.entities;

import formfiller.enums.ContentConstraint;

public class ValueMinimum extends ValueBoundary {
	ContentConstraint name = ContentConstraint.VALUE_MINIMUM;

	public ValueMinimum(Object minimum) {
		super(minimum);
	}
	
	public boolean isLegalComparisonResult(int comparisonResult){
		return comparisonResult >= 0;
	}
}
