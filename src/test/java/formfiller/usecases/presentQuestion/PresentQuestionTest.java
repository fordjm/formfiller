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
import formfiller.entities.Prompt;
import formfiller.request.implementations.RequestImpl;
import formfiller.request.interfaces.Request;
import formfiller.usecases.presentQuestion.PresentQuestionUseCase;
import formfiller.utilities.TestSetup;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class PresentQuestionTest {
	private PresentQuestionUseCase presentQuestionUseCase;
	private Request mockRequest;
	Prompt foundQuestion;

	private Prompt findQuestionByIndex(int index) {
		return ApplicationContext.questionGateway.findQuestionByIndex(index);
	}
	private PresentableQuestion getPresentableQuestion() {
		return (PresentableQuestion)
				ApplicationContext.questionPresenter.getPresentableResponse();
	}
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		mockRequest = Mockito.mock(RequestImpl.class);
		presentQuestionUseCase = new PresentQuestionUseCase();
	}
	public class GivenNoQuestions{
		
		@Test
		public void whenPresentQuestionRuns_ThenGetQuestionGetsAStartPrompt(){
			foundQuestion = findQuestionByIndex(0);
			
			presentQuestionUseCase.execute(mockRequest);
			
			assertThat(getPresentableQuestion().getId(), is(foundQuestion.getId()));
			assertThat(getPresentableQuestion().getMessage(), is(foundQuestion.getContent()));
		}
		
	}
	public class GivenAQuestion{
		
		@Before
		public void givenAQuestion() {
			ApplicationContext.questionGateway.save(MockCreation.makeMockNameQuestion());
		}
		@Test
		public void whenPresentQuestionRuns_ThenGetQuestionGetsGivenQuestion(){
			foundQuestion = findQuestionByIndex(0);
			
			presentQuestionUseCase.execute(mockRequest);
			
			assertThat(getPresentableQuestion().getId(), is(foundQuestion.getId()));
			assertThat(getPresentableQuestion().getMessage(), is(foundQuestion.getContent()));
		}
		
	}
}
