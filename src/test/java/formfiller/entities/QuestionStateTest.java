package formfiller.entities;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class QuestionStateTest {
	private QuestionState questionState;
	int questionIndex;
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		ApplicationContext.questionGateway.save(MockCreation.makeMockNameQuestion());
		ApplicationContext.questionGateway.save(MockCreation.makeMockAgeQuestion());
		questionIndex = 0;
		questionState = new QuestionState(questionIndex);
	}
	
	public class GivenANewFormState {
		private Prompt foundQuestion;
		
		private Prompt findGatewayQuestionByIndex(int index) {
			return ApplicationContext.questionGateway.findQuestionByIndex(index);
		}

		@Test
		public void canGetPrevQuestion() {
			Prompt prevQuestion = questionState.findQuestionByIndexOffset(-1);
			foundQuestion = findGatewayQuestionByIndex(-1);
			
			assertThat(prevQuestion, is(foundQuestion));
		}
		@Test
		public void canGetCurrentQuestion() {
			Prompt currentQuestion = questionState.getQuestion();
			foundQuestion = findGatewayQuestionByIndex(questionIndex);
			
			assertThat(currentQuestion, is(foundQuestion));
		}
		@Test
		public void canFindQuestionByIndexOffset() {
			Prompt newQuestion = questionState.findQuestionByIndexOffset(1);
			foundQuestion = findGatewayQuestionByIndex(questionIndex + 1);
			
			assertThat(newQuestion, is(foundQuestion));
		}
	}
	

}
