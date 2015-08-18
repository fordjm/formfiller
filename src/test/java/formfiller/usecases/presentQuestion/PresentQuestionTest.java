package formfiller.usecases.presentQuestion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableQuestion;
import formfiller.usecases.presentQuestion.PresentQuestionUseCase;
import formfiller.usecases.Request;
import formfiller.usecases.RequestBuilderImpl.PresentQuestionRequest;
import formfiller.utilities.TestSetup;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class PresentQuestionTest {
	private PresentQuestionUseCase presentQuestionUseCase;
	private Request mockRequest;
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
			presentQuestionUseCase.execute(mockRequest);
			presentedQuestion = (PresentableQuestion)
					ApplicationContext.questionPresenter.getPresentableResponse();
			assertThat(presentedQuestion.getId(), is("start"));
			assertThat(presentedQuestion.getMessage(), 
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
			presentQuestionUseCase.execute(mockRequest);
			presentedQuestion = (PresentableQuestion)
					ApplicationContext.questionPresenter.getPresentableResponse();
			assertThat(presentedQuestion.getId(), is("name"));
			assertThat(presentedQuestion.getMessage(), is("What is your name?"));
		}
	}
}
