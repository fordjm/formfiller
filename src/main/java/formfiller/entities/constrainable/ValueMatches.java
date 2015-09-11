package formfiller.entities.constrainable;

import formfiller.entities.Constrainable;

//	TODO:	Change to ValueOnBoundary?
public class ValueMatches implements Constrainable {
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