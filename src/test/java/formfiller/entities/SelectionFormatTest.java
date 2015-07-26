package formfiller.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public abstract class SelectionFormatTest<T> {

	protected SelectionFormat<T> format;
	protected List<T> selections;

	protected void assertInvalidResponse(T r) {
		assertFalse(format.satisfiesConstraint(r));
	}

	protected void assertValidResponse(T r) {
		assertTrue(format.satisfiesConstraint(r));
	}
	
	protected abstract SelectionFormat<T> makeFormat();
	
	protected abstract List<T> makeSelections();
			
	@Before
	public void setUp() throws Exception {
		selections = makeSelections();
		format = makeFormat();
	}

	@Test
	public void givenNull_isNotValidResponse() {		
		assertInvalidResponse(null);
	}
	
	@Test
	public void givenFirstSelection_isValidResponse() {
		assertValidResponse(selections.get(0));
	}
	
	@Test
	public void givenSecondSelection_isValidResponse() {
		assertValidResponse(selections.get(1));
	}
	
	@Test
	public void givenThirdSelection_isValidResponse() {
		assertValidResponse(selections.get(2));
	}
	
	@Test
	public abstract void givenNonSelection_isNotValidResponse();
}
