package formfiller.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NullPromptTest<T> {

	private Prompt n;
	
	@Before
	public void setup(){
		n = new NullPrompt();
	}

	@Test
	public void contentReturnsEmptyString() {
		assertEquals("", n.content());
	}
	
	@Test
	public void idReturnsEmptyString() {
		assertEquals("", n.id());
	}
}
