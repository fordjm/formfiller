package formfiller.gateways;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.entities.Question;
import formfiller.entities.NoQuestion;
import formfiller.entities.Prompt;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class InMemoryQuestionGatewayTest {
	private Prompt question;

	private void assertThatQuestionIsInstanceOfClass(Class<?> clazz) {
		assertThat(question, is(instanceOf(clazz)));
	}
	
	@Before
	public void setUp(){
		TestSetup.setupContext();		
	}

	public class GivenNoQuestions {
		
		@Test
		public void test() {
			question = ApplicationContext.questionGateway.findQuestionByIndex(0);
			
			assertThatQuestionIsInstanceOfClass(NoQuestion.class);
		}
		
	}
	
	public class GivenAQuestion {
		
		@Before
		public void givenAQuestion(){
			ApplicationContext.questionGateway.save(
					MockCreation.makeMockNameQuestion());
		}
		@Test
		public void test(){
			question = ApplicationContext.questionGateway.findQuestionByIndex(0);
			
			assertThatQuestionIsInstanceOfClass(Question.class);
		}
		
	}
}
