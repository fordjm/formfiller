package formfiller.entities;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SelectionFormatStringTest extends SelectionFormatTest<String> {

	@Override
	protected SelectionFormat<String> makeFormat() {
		return new SelectionFormat<String>(selections);
	}

	@Override
	protected List<String> makeSelections() {
		return Arrays.asList(new String[]{"a", "b", "c"});
	}

	@Test
	public void givenEmptyString_isNotValidResponse() {
		assertInvalidResponse("");		
	}
	
	@Override
	@Test
	public void givenNonSelection_isNotValidResponse() {
		assertInvalidResponse("x");
	}

	@Test
	public void givenMultipleSelectionString_isNotValidResponse() {
		assertInvalidResponse("a b");
	}
}
