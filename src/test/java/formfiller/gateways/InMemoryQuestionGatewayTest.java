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

	private QuestionGateway getQuestionGatewayFromApplicationContext() {
		return ApplicationContext.questionGateway;
	}
	private Prompt getQuestionFromQuestionGateway(QuestionGateway questionGateway) {
		return questionGateway.getQuestion();
	}
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
			QuestionGateway questionGateway = getQuestionGatewayFromApplicationContext();
			question = getQuestionFromQuestionGateway(questionGateway);
			
			assertThatQuestionIsInstanceOfClass(NoQuestion.class);
		}
		
	}
	
	public class GivenAnQuestion {
		
		@Before
		public void givenAnQuestion(){
			ApplicationContext.questionGateway.save(
					MockCreation.makeMockNameQuestion());
			ApplicationContext.questionGateway.findQuestionByIndexOffset(1);
		}
		@Test
		public void test(){
			QuestionGateway questionGateway = getQuestionGatewayFromApplicationContext();
			question = getQuestionFromQuestionGateway(questionGateway);
			
			assertThatQuestionIsInstanceOfClass(Question.class);
		}
		
	}
}
