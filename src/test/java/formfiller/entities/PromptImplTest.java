package formfiller.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PromptImplTest {
	
	PromptImpl promptImpl;
	final String id = "name";
	final String content = "What is your name?";

	@Before
	public void givenAPromptImplWithStringsIdAndContent(){
		promptImpl = new PromptImpl(id, content);
	}
	
	@Test
	public void whenGetIdRuns_ThenIdEqualsGivenString(){
		assertEquals(id, promptImpl.getId());
	}
	
	@Test 
	public void whenGetContentRuns_ThenContentEqualsGivenString(){
		assertEquals(content, promptImpl.getContent());
	}
}
