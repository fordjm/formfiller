package formfiller.entities.constrainable;

import formfiller.enums.ContentConstraint;

public class ValueMaximum extends ValueBoundary {
	ContentConstraint name = ContentConstraint.VALUE_UNDER;
	
	public ValueMaximum(Object maximum) {
		super(maximum);
	}

	public boolean isLegalComparisonResult(int comparisonResult){
		return comparisonResult >= 0;
	}
	
}
