package formfiller.usecases;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.boundaries.QuestionPresentation;
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
		public void whenPresentQuestionRuns_ThenGetQuestionGetsAStartPrompt(){
			presentQuestionUseCase.requestPresentableQuestion();
			assertThat(QuestionPresentation.presentableQuestion.getId(), is("start"));
			assertThat(QuestionPresentation.presentableQuestion.getContent(), 
					is("You have reached the start of this form."));
		}
	}
	public class GivenAQuestion{
		@Before
		public void givenAQuestion() {
			ApplicationContext.questionGateway.save(TestUtil.makeMockNameQuestion());
			ApplicationContext.questionGateway.findQuestionByIndexOffset(1);
		}
		@Test
		public void whenPresentQuestionRuns_ThenGetQuestionGetsGivenQuestion(){
			presentQuestionUseCase.requestPresentableQuestion();
			assertThat(QuestionPresentation.presentableQuestion.getId(), is("name"));
			assertThat(QuestionPresentation.presentableQuestion.getContent(), 
					is("What is your name?"));
		}
	}
}
