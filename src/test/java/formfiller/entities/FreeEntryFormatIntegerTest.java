package formfiller.entities;

import static org.junit.Assert.assertTrue;

public class FreeEntryFormatIntegerTest extends FreeEntryFormatTest<Integer> {

	@Override
	protected FreeEntryFormat<Integer> makeFormat() {
		return new FreeEntryFormat<Integer>();
	}

	@Override
	public void givenProperDataType_isValidResponse() {
		assertTrue(format.satisfiesConstraint(0123456));
	}

}
