package formfiller.entities;

import static org.junit.Assert.assertTrue;

public class FreeEntryFormatStringTest extends FreeEntryFormatTest<String> {

	@Override
	protected FreeEntryFormat<String> makeFormat() {
		return new FreeEntryFormat<String>();
	}

	public void givenEmptyString_isValidResponseReturnsTrue() {
		assertTrue(format.satisfiesConstraint(""));
	}

	@Override
	public void givenProperDataType_isValidResponse() {
		assertTrue(format.satisfiesConstraint("x"));
	}
}
