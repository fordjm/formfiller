package formfiller.entities;

import static org.junit.Assert.assertTrue;

import formfiller.utilities.TestUtil;

public class FreeEntryFormatIntegerTest extends FreeEntryFormatTest<Integer> {

	@Override
	protected FreeEntryFormat<Integer> makeFormat() {
		ResponseImpl<Integer> mockResponse = TestUtil.createMockIntegerResponseImpl(0, 2);
		return new FreeEntryFormat<Integer>(mockResponse);
	}

	@Override
	public void givenProperDataType_isValidResponse() {
		assertTrue(format.satisfiesConstraint());
	}
}
