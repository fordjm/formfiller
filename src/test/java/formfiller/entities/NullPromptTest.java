package formfiller.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NullPromptTest<T> {
	private NullPrompt nullPrompt;
	
	@Before
	public void givenNullPrompt(){
		nullPrompt = new NullPrompt();
	}
	
	@Test
	public void whenGetIdRuns_ThenIdEqualsEmptyString() {
		assertEquals("", nullPrompt.getId());
	}

	@Test
	public void whenGetContentRuns_ThenContentEqualsEmptyString() {
		assertEquals("", nullPrompt.getContent());
	}
}
