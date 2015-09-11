package formfiller.entities.constrainable;

import formfiller.enums.ContentConstraint;

public class ValueOverBoundary extends ValueBoundary {
	ContentConstraint name = ContentConstraint.VALUE_OVER;

	public ValueOverBoundary(Object minimum) {
		super(minimum);
	}
	
	public boolean isLegalComparisonResult(int comparisonResult){
		return comparisonResult > 0;
	}
}
