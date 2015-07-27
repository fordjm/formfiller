package formfiller.entities;

import java.util.Arrays;
import java.util.List;

import formfiller.utilities.TestUtil;

public class SelectionFormatIntegerTest extends SelectionFormatTest<Integer> {

	@Override
	protected SelectionFormat<Integer> makeFormat() {
		ResponseImpl<Integer> mockResponse = TestUtil.createMockIntegerResponseImpl(0, 2);
		return new SelectionFormat<Integer>(mockResponse, selections);
	}

	@Override
	protected List<Integer> makeSelections() {
		return Arrays.asList(1, 2, 3);
	}

	@Override
	public void givenSelection_isValidResponse() {
		assertValidResponse();
	}
}
