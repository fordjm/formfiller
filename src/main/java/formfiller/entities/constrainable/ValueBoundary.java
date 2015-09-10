package formfiller.entities.constrainable;

import formfiller.entities.Constrainable;

public abstract class ValueBoundary implements Constrainable {
	private Object boundaryValue;
	
	public ValueBoundary(Object boundaryValue) {
		this.boundaryValue = boundaryValue;
	}

	public boolean isSatisfiedBy(Object candidate) {
		return isComparable(candidate) && isLegalValue(candidate);
	}

	private boolean isComparable(Object candidate) {
		return candidate instanceof Comparable;
	}

	private boolean isLegalValue(Object candidate){
		Comparable<Object> castValue = (Comparable<Object>) candidate;
		int comparisonResult = castValue.compareTo(boundaryValue);
		return isLegalComparisonResult(comparisonResult);
	}

	protected abstract boolean isLegalComparisonResult(int comparisonResult);
}