package formfiller.usecases.presentQuestion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.usecases.presentQuestion.PresentQuestionUseCase;
import formfiller.usecases.presentQuestion.PresentableQuestion;
import formfiller.usecases.presentQuestion.PresentQuestionRequestFactoryImpl.PresentQuestionRequest;
import formfiller.utilities.TestSetup;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class PresentQuestionTest {
	private PresentQuestionUseCase presentQuestionUseCase;
	private PresentQuestionRequest mockRequest;
	private PresentableQuestion presentedQuestion;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		mockRequest = Mockito.mock(PresentQuestionRequest.class);
		presentQuestionUseCase = new PresentQuestionUseCase();
	}
	public class GivenNoQuestions{
		@Test
		public void whenPresentQuestionRuns_ThenGetQuestionGetsAStartPrompt(){
			presentQuestionUseCase.presentQuestion(mockRequest);
			presentedQuestion = 
					ApplicationContext.presentQuestionResponseBoundary.getPresentableQuestion();
			assertThat(presentedQuestion.getId(), is("start"));
			assertThat(presentedQuestion.getContent(), 
					is("You have reached the start of this form."));
		}
	}
	public class GivenAQuestion{
		@Before
		public void givenAQuestion() {
			ApplicationContext.questionGateway.save(MockCreation.makeMockNameQuestion());
			ApplicationContext.questionGateway.findQuestionByIndexOffset(1);
		}
		@Test
		public void whenPresentQuestionRuns_ThenGetQuestionGetsGivenQuestion(){
			presentQuestionUseCase.presentQuestion(mockRequest);
			presentedQuestion = 
					ApplicationContext.presentQuestionResponseBoundary.getPresentableQuestion();
			assertThat(presentedQuestion.getId(), is("name"));
			assertThat(presentedQuestion.getContent(), is("What is your name?"));
		}
	}
}
