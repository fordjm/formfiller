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
		question.setResponseRequired(false);
	}
	
	@Test
	public void questionFieldsHaveExpectedValues(){
		assertThat(question.getId(), is(ID));
		assertThat(question.getContent(), is(CONTENT));
		assertThat(question.hasAnswer(), is(false));
		assertThat(question.requiresAnswer(), is(false));
	}
}
