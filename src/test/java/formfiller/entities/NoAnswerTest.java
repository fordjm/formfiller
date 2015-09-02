package formfiller.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoAnswerTest {
	private final int id = -1;
	private final String content = "";

	@Test
	public void whenGetIdRuns_ThenIdEqualsNegativeOne() {
		assertEquals(id, AnswerImpl.NONE.getId());
	}

	@Test
	public void whenGetContentRuns_ThenContentEqualsEmptyString() {
		assertEquals(content, AnswerImpl.NONE.getContent());
	}

	@Test
	public void whenSatisfiesConstraintRuns_ThenItReturnsFalse() {
		assertFalse(AnswerImpl.NONE.satisfiesConstraint());
	}
}
