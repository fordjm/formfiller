package formfiller.gateways;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.entities.Answer;
import formfiller.entities.NoAnswer;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class InMemoryAnswerGatewayTest {
	private Answer answer;

	private void assertThatAnswerIsInstanceOfClass(Class<?> clazz) {
		assertThat(answer, is(instanceOf(clazz)));
	}
	
	@Before
	public void setUp(){
		TestSetup.setupContext();		
	}

	public class GivenNoAnswers {
		
		@Test
		public void test() {			
			answer = ApplicationContext.answerGateway.findAnswerByIndex(0);
			
			assertThatAnswerIsInstanceOfClass(NoAnswer.class);
		}
		
	}
	
	public class GivenAnAnswer {
		
		@Before
		public void givenAnAnswer(){
			ApplicationContext.answerGateway.save(
					MockCreation.makeMockNameAnswer("nameAnswer"));
			ApplicationContext.currentAnswerState.findAnswerByIndexOffset(1);
		}
		@Test
		public void test(){
			answer = ApplicationContext.answerGateway.findAnswerByIndex(0);
			
			assertThatAnswerIsInstanceOfClass(Answer.class);
		}
		
	}
}
