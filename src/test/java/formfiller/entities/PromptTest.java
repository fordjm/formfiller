package formfiller.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PromptTest {

	private Prompt p;

	private Prompt createPrompt(String id, String content) {
		return new PromptImpl(id, content);
	}
	
	private Prompt createNamePrompt() {
		return createPrompt("name", "What is your name?");
	}
	
	@Before
	public void setup(){
		p = createNamePrompt();
	}
	
	@Test 
	public void givenStringId_idIsNewString(){
		assertEquals("name", p.id());
	}
	
	@Test 
	public void givenStringContent_contentIsNewString(){
		assertEquals("What is your name?", p.content());
	}
}
