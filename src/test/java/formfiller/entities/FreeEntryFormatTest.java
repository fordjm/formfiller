package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FreeEntryFormatTest {
	
	private FreeEntryFormat format;

	@Before
	public void setup(){
		format = new FreeEntryFormat();
	}

	@Test
	public void givenNull_isValidResponseReturnsFalse() {
		assertFalse(format.satisfiesConstraint(null));
	}
	
	@Test
	public void givenEmptyString_isValidResponseReturnsTrue(){
		assertTrue(format.satisfiesConstraint(""));
	}
	
	@Test
	public void givenNonEmptyString_isValidResponseReturnsTrue(){
		assertTrue(format.satisfiesConstraint("x"));
	}
}
