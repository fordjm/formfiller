package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.*;

import formfiller.Context;
import formfiller.entities.QuestionImpl.NullObjectFieldValueSet;
import formfiller.entities.formComponent.FormComponent;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class QuestionTest {
	Question question;
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		FormComponent unfound = Context.formComponentGateway.find("unfoundId");
		question = unfound.question;
	}

	@Test
	public void testNull() {
		assertThat(question.getId(), is("null"));
		assertThat(question.getContent(), is("No such question exists."));
		assertThat(question.isValid(), is(false));
		assertThat(question, is(QuestionImpl.NULL));
	}
	
	@Test(expected = NullObjectFieldValueSet.class) 
	public void settingIdThrowsException() {
		question.setId("id");
	}
	
	@Test(expected = NullObjectFieldValueSet.class) 
	public void settingContentThrowsException() {
		question.setContent("content");
	}
	
	public class StartQuestionContext {
		@Before
		public void setUp() {
			question = QuestionImpl.START;
		}

		@Test
		public void testStart() {
			assertThat(question.getId(), is("start"));
			assertThat(question.getContent(), 
					is("You have reached the start of this form."));
			assertThat(question.isValid(), is(false));
			assertThat(question, is(QuestionImpl.START));
		}
		
	}
	
	public class EndQuestionContext {
		@Before
		public void setUp() {
			question = QuestionImpl.END;
		}

		@Test
		public void testStart() {
			assertThat(question.getId(), is("end"));
			assertThat(question.getContent(), 
					is("You have reached the end of this form."));
			assertThat(question.isValid(), is(false));
			assertThat(question, is(QuestionImpl.END));
		}
		
	}
	
	public class EmptyQuestionContext {
		@Before
		public void setUp() {
			question = new QuestionImpl();
		}
		
		@Test
		public void testEmptyQuestion() {
			assertThat(question.getId(), is(""));
			assertThat(question.getContent(), is(""));
			assertThat(question.isValid(), is(false));
		}
		
		@Test
		public void settingIdSetsNewId() {
			question.setId("newId");
			
			assertThat(question.getId(), is("newId"));
		}
		
		@Test
		public void settingContentSetsNewContent() {
			question.setContent("newContent");
			
			assertThat(question.getContent(), is("newContent"));
		}
		
	}
	
	public class QuestionWithFieldValuesContext {
		@Before
		public void setUp() {
			question = new QuestionImpl();
			question.setId("id");
		}
		
		@Test
		public void testQuestionWithIdOnly() {
			assertThat(question.getId(), is("id"));
			assertThat(question.getContent(), is(""));
			assertThat(question.isValid(), is(false));
		}
		
		@Test
		public void testQuestionWithFieldValues() {
			question.setContent("content");
			
			assertThat(question.getId(), is("id"));
			assertThat(question.getContent(), is("content"));
			assertThat(question.isValid(), is(true));
		}
		
	}
}
