package formfiller.entities;

//	TODO:	Format credit to Clean Code Chapter 25
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.Context;
import formfiller.entities.formComponent.FormComponent;

@RunWith(HierarchicalContextRunner.class)
public class AnswerTest {
	private Answer answer;
	
	@Before
	public void setUp() {
		FormComponent unfound = Context.formComponentGateway.find("unfoundId");
		answer = unfound.answer;
	}

	@Test
	public void testNull() {		
		assertThat(answer.getId().equals(""), is(true));
		assertThat(answer.getContent().equals(""), is(true));
		assertThat(answer.isValid(), is(false));
		assertThat(answer, is(AnswerImpl.NONE));
	}
	
	@Test(expected = AnswerImpl.NullObjectFieldValueSet.class)
	public void settingIdThrowsException() {
		answer.setId("id");
	}
	
	@Test(expected = AnswerImpl.NullObjectFieldValueSet.class)
	public void settingContentThrowsException() {
		answer.setContent("content");
	}
	
	public class EmptyAnswerContext {
		@Before
		public void setUp() {
			answer = new AnswerImpl();
		}
		
		@Test
		public void testEmptyAnswer() {
			assertThat(answer.getId(), is(""));
			assertThat(answer.getContent().equals(""), is(true));
			assertThat(answer.isValid(), is(false));
			assertThat(answer, not(AnswerImpl.NONE));
		}
		
	}
	
	public class AnswerWithFieldValuesContext {
		@Before
		public void setUp() {
			answer = new AnswerImpl();
			answer.setId("id");
			answer.setContent("content");
		}
		
		@Test
		public void testAnswerWithFieldValues() {
			assertThat(answer.getId(), is("id"));
			assertThat(answer.getContent().equals("content"), is(true));
			assertThat(answer.isValid(), is(true));
			assertThat(answer, not(AnswerImpl.NONE));
		}
		
		@Test
		public void settingIdSetsNewId() {
			answer.setId("newId");
			
			assertThat(answer.getId(), is("newId"));
		}
		
		@Test
		public void settingContentSetsNewContent() {
			answer.setContent("newContent");
			
			assertEquals("newContent", answer.getContent());
		}
		
	}
}
