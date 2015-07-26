package formfiller.entities;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SelectionFormatTest {

	List<String> selections;
	SelectionFormat s;

	private void assertInvalidResponse(String r) {
		assertFalse(s.satisfiesConstraint(r));
	}

	private void assertValidResponse(String r) {
		assertTrue(s.satisfiesConstraint(r));
	}
			
	@Before
	public void setup(){
		selections = Arrays.asList(new String[]{"a", "b", "c"});
		s = new SelectionFormat(selections);
	}

	@Test
	public void givenNull_isNotValidResponse() {		
		assertInvalidResponse(null);
	}

	@Test
	public void givenEmptyString_isNotValidResponse() {
		assertInvalidResponse("");		
	}
	
	@Test
	public void givenNonSelectionString_isNotValidResponse() {
		assertInvalidResponse("x");
	}
	
	@Test
	public void givenFirstSelectionString_isValidResponse() {
		assertValidResponse("a");
	}
	
	@Test
	public void givenSecondSelectionString_isValidResponse() {
		assertValidResponse("b");
	}
	
	@Test
	public void givenThirdSelectionString_isValidResponse() {
		assertValidResponse("c");
	}
	
	@Test
	public void givenMultipleSelectionString_isNotValidResponse() {
		assertInvalidResponse("a b");
	}
}
