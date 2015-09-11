package formfiller.deprecated;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoConstrainableAnswerTest {
	private final int id = -1;
	private final String content = "";

	@Test
	public void whenGetIdRuns_ThenIdEqualsNegativeOne() {
		assertEquals(id, ConstrainableAnswer.NONE.getId());
	}

	@Test
	public void whenGetContentRuns_ThenContentEqualsEmptyString() {
		assertEquals(content, ConstrainableAnswer.NONE.getContent());
	}

	@Test
	public void whenSatisfiesConstraintRuns_ThenItReturnsFalse() {
		assertFalse(ConstrainableAnswer.NONE.isSatisfied());
	}
}
