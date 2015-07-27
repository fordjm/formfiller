package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public abstract class SelectionFormatTest<T> {

	protected SelectionFormat<T> format;
	protected List<T> selections;

	protected void assertInvalidResponse() {
		assertFalse(format.satisfiesConstraint());
	}

	protected void assertValidResponse() {
		assertTrue(format.satisfiesConstraint());
	}
	
	protected abstract SelectionFormat<T> makeFormat();
	
	protected abstract List<T> makeSelections();
			
	@Before
	public void setUp() throws Exception {
		selections = makeSelections();
		format = makeFormat();
	}
	
	@Test
	public abstract void givenSelection_isValidResponse();
}
