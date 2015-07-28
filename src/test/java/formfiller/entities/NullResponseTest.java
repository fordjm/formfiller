package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NullResponseTest {
	private NullResponse nullResponse;
	
	@Before
	public void givenNullResponse(){
		nullResponse = new NullResponse();
	}

	@Test
	public void whenGetIdRuns_ThenIdEqualsNegativeOne() {
		assertEquals(-1, nullResponse.getId());
	}

	@Test
	public void whenGetContentRuns_ThenContentEqualsEmptyString() {
		assertEquals("", nullResponse.getContent());
	}

	@Test
	public void whenSatisfiesConstraintRuns_ThenItReturnsFalse() {
		assertFalse(nullResponse.satisfiesConstraint());
	}
}
