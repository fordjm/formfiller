package formfiller.entities.constrainable;

import formfiller.enums.ContentConstraint;

public class ValueMinimum extends ValueBoundary {
	ContentConstraint name = ContentConstraint.VALUE_OVER;

	public ValueMinimum(Object minimum) {
		super(minimum);
	}
	
	public boolean isLegalComparisonResult(int comparisonResult){
		return comparisonResult <= 0;
	}
	
}
