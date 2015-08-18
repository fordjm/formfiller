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

	private AnswerGateway getAnswerGatewayFromApplicationContext() {
		return ApplicationContext.answerGateway;
	}
	private Answer getAnswerFromAnswerGateway(AnswerGateway answerGateway) {
		return answerGateway.getAnswer();
	}
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
			AnswerGateway answerGateway = getAnswerGatewayFromApplicationContext();
			answer = getAnswerFromAnswerGateway(answerGateway);
			
			assertThatAnswerIsInstanceOfClass(NoAnswer.class);
		}
		
	}
	
	public class GivenAnAnswer {
		
		@Before
		public void givenAnAnswer(){
			ApplicationContext.answerGateway.save(
					MockCreation.makeMockNameResponse("nameAnswer"));
			ApplicationContext.answerGateway.findAnswerByIndexOffset(1);
		}
		@Test
		public void test(){
			AnswerGateway answerGateway = getAnswerGatewayFromApplicationContext();
			answer = getAnswerFromAnswerGateway(answerGateway);
			
			assertThatAnswerIsInstanceOfClass(Answer.class);
		}
		
	}
}
