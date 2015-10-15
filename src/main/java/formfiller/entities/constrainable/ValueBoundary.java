package formfiller.entities.constrainable;

public abstract class ValueBoundary implements Constrainable {
	private Object boundaryValue;
	
	//	TODO:	Why not take a Comparable here?
	//			A:  Because Comparable requires generic type that propagates 
	//				upward and isComparable checks type before cast.
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
		Comparable<Object> illegalCast = (Comparable<Object>) boundaryValue;
		int comparisonResult = illegalCast.compareTo(candidate);
		return isLegalComparisonResult(comparisonResult);
	}

	protected abstract boolean isLegalComparisonResult(int comparisonResult);
}