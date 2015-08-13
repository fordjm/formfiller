package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NullResponseTest {
	private NoAnswer nullResponse;
	private final int id = -1;
	private final String content = "";
	
	@Before
	public void givenANullResponse(){
		nullResponse = new NoAnswer();
	}

	@Test
	public void whenGetIdRuns_ThenIdEqualsNegativeOne() {
		assertEquals(id, nullResponse.getId());
	}

	@Test
	public void whenGetContentRuns_ThenContentEqualsEmptyString() {
		assertEquals(content, nullResponse.getContent());
	}

	@Test
	public void whenSatisfiesConstraintRuns_ThenItReturnsFalse() {
		assertFalse(nullResponse.satisfiesConstraint());
	}
}
