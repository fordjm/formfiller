package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.gateways.InMemoryQuestionGateway;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class FormStateTest {
	private FormState formState;
	
	private InMemoryQuestionGateway getQuestionGateway(){
		return (InMemoryQuestionGateway) ApplicationContext.questionGateway;
	}
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		ApplicationContext.questionGateway.save(MockCreation.makeMockNameQuestion());
		ApplicationContext.questionGateway.save(MockCreation.makeMockAgeQuestion());
		formState = new FormState(-1);
	}
	
	public class GivenANewFormState {
		@Test
		public void canGetCurrentQuestion() {
			Prompt currentQuestion = formState.getQuestion();
			
			assertThat(currentQuestion, is(instanceOf(NoQuestion.class)));
		}
		@Test
		public void canFindQuestionByIndexOffset() {
			Prompt newQuestion = formState.findQuestionByIndexOffset(1);
			
			assertThat(getQuestionGateway().findQuestionByIndex(0), is(newQuestion));
		}
	}
	

}
