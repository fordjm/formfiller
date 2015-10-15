package formfiller.entities.constrainable;

//	TODO:	Change to ValueOnBoundary?
public class ValueEquals implements Constrainable {
	Object valueToMatch;

	public boolean isSatisfiedBy(Object objectUnderTest) {
		if (objectUnderTest == null) return false;
		
		return isLegalValue(objectUnderTest);
	}

	private boolean isLegalValue(Object objectUnderTest) {
		return objectUnderTest == valueToMatch ? true : false;
	}

	public void setValue(Object valueToMatch) {
		if (valueToMatch == null) throw new NullValueSet();
		
		this.valueToMatch = valueToMatch;
	}
	
	@SuppressWarnings("serial")
	public class NullValueSet extends RuntimeException { }
}
