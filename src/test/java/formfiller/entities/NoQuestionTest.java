package formfiller.entities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NoQuestionTest {
	private NoQuestion noQuestion;
	private final String emptyString = "";
	
	@Before
	public void givenANoQuestion(){
		noQuestion = new NoQuestion();
	}
	@Test
	public void noQuestionFieldsHaveExpectedValues(){
		assertThat(noQuestion.getId(), is(emptyString));
		assertThat(noQuestion.getContent(), is(emptyString));
		assertThat(noQuestion.hasAnswer(), is(false));
		assertThat(noQuestion.requiresAnswer(), is(false));
	}
}
