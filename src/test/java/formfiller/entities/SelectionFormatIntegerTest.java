package formfiller.entities;

import java.util.Arrays;
import java.util.List;

public class SelectionFormatIntegerTest extends SelectionFormatTest<Integer> {

	@Override
	protected SelectionFormat<Integer> makeFormat() {
		return new SelectionFormat<Integer>(selections);
	}

	@Override
	protected List<Integer> makeSelections() {
		return Arrays.asList(new Integer[]{ 1, 2, 3});
	}

	@Override
	public void givenNonSelection_isNotValidResponse() {
		assertInvalidResponse(35);
	}
}
