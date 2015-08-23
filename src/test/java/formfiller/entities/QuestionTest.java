package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
	
	Question question;
	final String id = "name";
	final String content = "What is your name?";

	@Before
	public void givenAQuestion(){
		question = new Question(id, content);
		question.setResponseRequired(false);
	}
	@Test
	public void questionFieldsHaveExpectedValues(){
		assertThat(question.getId(), is(id));
		assertThat(question.getContent(), is(content));
		assertThat(question.hasAnswer(), is(false));
		assertThat(question.requiresAnswer(), is(false));
	}
}
