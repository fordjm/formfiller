package formfiller.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NullPromptTest<T> {
	private NullPrompt nullPrompt;
	private final String emptyString = "";
	
	@Before
	public void givenANullPrompt(){
		nullPrompt = new NullPrompt();
	}
	
	@Test
	public void whenGetIdRuns_ThenIdEqualsEmptyString() {
		assertEquals(emptyString, nullPrompt.getId());
	}

	@Test
	public void whenGetContentRuns_ThenContentEqualsEmptyString() {
		assertEquals(emptyString, nullPrompt.getContent());
	}
}
