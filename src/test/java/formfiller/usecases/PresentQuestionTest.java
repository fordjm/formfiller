package formfiller.usecases;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Question;
import formfiller.gateways.Context;
import formfiller.transactions.Transaction;
import formfiller.usecases.PresentQuestion;
import formfiller.utilities.TestSetup;

@RunWith(HierarchicalContextRunner.class)
public class PresentQuestionTest {
	private Transaction presentQuestion;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		presentQuestion = new PresentQuestion();
	}
	public class GivenNoQuestions{
		private void clearQuestions(){
			Question current;
			Context.questionGateway.findQuestionByIndexOffset(1);
			while ((current = Context.questionGateway.getQuestion()) != null){
				Context.questionGateway.delete(current);
				Context.questionGateway.findQuestionByIndexOffset(1);
			}
		}
		@Before
		public void givenNoQuestions(){
			clearQuestions();
		}
		@Test
		public void whenPresentQuestionRuns_ThenGetQuestionIsNull(){
			presentQuestion.execute();
			assertNull(Context.questionGateway.getQuestion());
		}
	}
	public class GivenAQuestion{
		@Before
		public void givenAQuestion() {
			Context.questionGateway.save(new Question());
		}	
		@Test
		public void beforeNextQuestionRuns_GetQuestionIsNull(){
			assertThat(Context.questionGateway.getQuestion(), equalTo(null));
		}
		public class GivenPresentQuestionHasRun{
			@Before
			public void givenPresentQuestionHasRun(){
				presentQuestion.execute();
			}
			@Test
			public void whenGetQuestionRuns_ThenItReturnsAQuestion() {
				assertThat(Context.questionGateway.getQuestion(), isA(Question.class));
			}
			public class GivenNextQuestionHasRunTwice{
				@Before
				public void givenNextQuestionHasRunTwice(){
					presentQuestion = new PresentQuestion();
					presentQuestion.execute();
				}
				@Test
				public void whenGetQuestionRuns_ThenItReturnsNull(){
					assertNull(Context.questionGateway.getQuestion());
				}
			}
		}
	}
}
