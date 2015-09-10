package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {	
	private Question question;
	private final String ID = "name";
	private final String CONTENT = "What is your name?";

	@Before
	public void givenAQuestion(){
		question = new Question(ID, CONTENT);
	}
	
	@Test
	public void questionFieldsHaveExpectedValues(){
		assertThat(question.id, is(ID));
		assertThat(question.content, is(CONTENT));
	}
}
