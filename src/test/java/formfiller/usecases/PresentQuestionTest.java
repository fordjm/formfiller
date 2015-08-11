package formfiller.usecases;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Question;
import formfiller.gateways.ApplicationContext;
import formfiller.utilities.TestSetup;
import formfiller.utilities.TestUtil;

@RunWith(HierarchicalContextRunner.class)
public class PresentQuestionTest {
	private PresentQuestionUseCase presentQuestionUseCase;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		presentQuestionUseCase = new PresentQuestionUseCase();
	}
	public class GivenNoQuestions{
		@Test
		public void whenPresentQuestionRuns_ThenGetQuestionGetsANullPrompt(){
			presentQuestionUseCase.requestQuestion();
			PresentableQuestion presentableQuestion = ApplicationContext.presentQuestionBoundary.getQuestion();
			assertThat(presentableQuestion.id, is(""));
			assertThat(presentableQuestion.content, is(""));
		}
	}
	public class GivenAQuestion{
		@Before
		public void givenAQuestion() {
			ApplicationContext.questionGateway.save(TestUtil.makeMockNameQuestion());
			ApplicationContext.questionGateway.findQuestionByIndexOffset(1);
		}
		@Test
		public void whenPresentQuestionRuns_ThenPresentableQuestionFieldsHaveExpectedValues(){
			presentQuestionUseCase.requestQuestion();
			PresentableQuestion presentableQuestion = ApplicationContext.presentQuestionBoundary.getQuestion();
			assertThat(presentableQuestion.id, is("name"));
			assertThat(presentableQuestion.content, is("What is your name?"));
		}
	}
	public class GivenMultipleQuestions{
		Question nameQuestion = TestUtil.makeMockNameQuestion();
		Question ageQuestion = TestUtil.makeMockAgeQuestion();
		private PresentableQuestion presentableQuestion;
		
		void setPresentableQuestion(int indexOffset) {
			presentQuestionUseCase.requestQuestion(indexOffset);
			presentableQuestion = ApplicationContext.presentQuestionBoundary.getQuestion();
		}
		
		@Before
		public void givenTwoQuestions() {
			ApplicationContext.questionGateway.save(nameQuestion);
			ApplicationContext.questionGateway.save(ageQuestion);
		}
		@Test
		public void whenCurrentQuestionIsPresented_ThenItsFieldsMatchNameQuestionFields(){
			setPresentableQuestion(0);
			assertThat(presentableQuestion.id, is(nameQuestion.getId()));
			assertThat(presentableQuestion.content, is(nameQuestion.getContent()));
		}
		@Test
		public void whenNextQuestionIsPresented_ThenItsFieldsMatchAgeQuestionFields(){
			setPresentableQuestion(1);
			assertThat(presentableQuestion.id, is(ageQuestion.getId()));
			assertThat(presentableQuestion.content, is(ageQuestion.getContent()));
		}
		@Test
		public void whenPrevQuestionIsPresented_ThenItsFieldsMatchNameQuestionFields(){
			setPresentableQuestion(-1);
			assertThat(presentableQuestion.id, is(nameQuestion.getId()));
			assertThat(presentableQuestion.content, is(nameQuestion.getContent()));
		}
	}
}
