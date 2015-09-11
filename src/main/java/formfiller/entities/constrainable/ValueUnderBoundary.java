package formfiller.entities.constrainable;

import formfiller.enums.ContentConstraint;

public class ValueUnderBoundary extends ValueBoundary {
	ContentConstraint name = ContentConstraint.VALUE_UNDER;
	
	public ValueUnderBoundary(Object maximum) {
		super(maximum);
	}

	public boolean isLegalComparisonResult(int comparisonResult){
		return comparisonResult < 0;
	}
}
