package formfiller.entities;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import formfiller.utilities.TestUtil;

public class SelectionFormatStringTest extends SelectionFormatTest<String> {

	@Override
	protected SelectionFormat<String> makeFormat() {
		ResponseImpl<String> mockResponse = TestUtil.createMockStringResponseImpl(0, "b");
		return new SelectionFormat<String>(mockResponse, selections);
	}

	@Override
	protected List<String> makeSelections() {
		return Arrays.asList("a", "b", "c");
	}

	@Test
	public void givenEmptyString_isValidResponse() {
		assertValidResponse();		
	}
	
	@Ignore
	@Test
	public void givenSelection_isValidResponse() {
	}

	@Ignore
	@Test
	public void givenMultipleSelectionString_isNotValidResponse() {
		assertInvalidResponse();
	}
}
