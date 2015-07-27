package formfiller.entities;

import static org.junit.Assert.assertTrue;

import formfiller.utilities.TestUtil;

public class FreeEntryFormatStringTest extends FreeEntryFormatTest<String> {
	
	private ResponseImpl<String> createMockResponseImpl(int id, String content){
		return TestUtil.createMockStringResponseImpl(id, content);
	}

	@Override
	protected FreeEntryFormat<String> makeFormat() {
		ResponseImpl<String> mockResponse = createMockResponseImpl(0, "");
		return new FreeEntryFormat<String>(mockResponse);
	}

	@Override
	public void givenProperDataType_isValidResponse() {
		assertTrue(format.satisfiesConstraint());
	}
}
