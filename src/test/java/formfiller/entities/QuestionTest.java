package formfiller.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
	
	Question question;
	final String id = "name";
	final String content = "What is your name?";

	@Before
	public void givenAPromptImpl(){
		question = new Question(id, content);
	}
	
	@Test
	public void whenGetIdRuns_ThenIdEqualsGivenString(){
		assertEquals(id, question.getId());
	}
	
	@Test 
	public void whenGetContentRuns_ThenContentEqualsGivenString(){
		assertEquals(content, question.getContent());
	}
}
