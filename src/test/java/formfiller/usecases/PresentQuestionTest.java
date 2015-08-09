package formfiller.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Question;
import formfiller.gateways.Context;
import formfiller.utilities.TestSetup;

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
			PresentableQuestion presentableQuestion = presentQuestionUseCase.presentQuestion();
			assertThat(presentableQuestion.id, is(""));
			assertThat(presentableQuestion.content, is(""));
		}
	}
	public class GivenAQuestion{
		@Before
		public void givenAQuestion() {
			Context.questionGateway.save(new Question("name", "What is your name?"));
			Context.questionGateway.findQuestionByIndexOffset(1);
		}
		@Test
		public void whenPresentQuestionRuns_ThenPresentableQuestionFieldsHaveExpectedValues(){
			PresentableQuestion presentableQuestion = presentQuestionUseCase.presentQuestion();
			assertThat(presentableQuestion.id, is("name"));
			assertThat(presentableQuestion.content, is("What is your name?"));
		}
	}
}
