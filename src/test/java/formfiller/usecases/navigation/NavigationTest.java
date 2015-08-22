package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.EndPrompt;
import formfiller.entities.Question;
import formfiller.entities.StartPrompt;
import formfiller.enums.ActionOutcome;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.usecases.navigation.NavigationUseCase;
import formfiller.ApplicationContext;
import formfiller.delivery.PresentableResponseImpl;
import formfiller.utilities.TestSetup;
import formfiller.utilities.MockCreation;

@RunWith(HierarchicalContextRunner.class)
public class NavigationTest {	
	private NavigationUseCase useCase;
	private NavigationRequest mockRequest;
	
	void setMockRequestOffset(int offset){
		when(mockRequest.getOffset()).thenReturn(offset);
	}
	
	@Before
	public void setUp(){
		TestSetup.setupContext();
		useCase = new NavigationUseCase();
		mockRequest = mock(NavigationRequest.class);		
	}
	
	public class GivenNoQuestions{
		
		public class GivenPrevQuestionRequest{
			
			@Before
			public void givenPrevQuestionRequest(){
				setMockRequestOffset(-1);
			}
			@Test
			public void gettingQuestionGetsStartPrompt(){
				useCase.execute(mockRequest);
				assertEquals(useCase, ApplicationContext.executedUseCases.peek().getUseCase());
				assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
						is(instanceOf(StartPrompt.class)));
			}
		}
		
		public class GivenCurrentQuestionRequest{
			
			@Before
			public void givenCurrentQuestionRequest(){
				setMockRequestOffset(0);
			}
			@Test
			public void gettingQuestionGetsStartPrompt(){
				useCase.execute(mockRequest);
				assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
						is(instanceOf(StartPrompt.class)));
			}
			
		}
		
		public class GivenNextQuestionRequest{
			
			@Before
			public void givenNextQuestionRequest(){
				setMockRequestOffset(1);
			}
			@Test
			public void gettingQuestionGetsEndPrompt(){
				useCase.execute(mockRequest);
				assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
						is(instanceOf(EndPrompt.class)));
			}
			
		}
	}
	
	public class GivenOneQuestion{
		Question mockQuestion;
		
		private void navigateToFirstQuestion(){
			setMockRequestOffset(1);
			useCase.execute(mockRequest);
		}
		
		public class GivenAnswerNotRequired{
			
			@Before
			public void givenAnswerNotRequired(){
				mockQuestion = MockCreation.makeMockNameQuestion();
				ApplicationContext.questionGateway.save(mockQuestion);
			}
			
			public class GivenFirstNextQuestionRequest{
				
				@Before
				public void givenNextQuestionRequest(){
					setMockRequestOffset(1);
				}
				@Test
				public void canNavigateToFirstQuestion() {
					useCase.execute(mockRequest);
					assertEquals(mockQuestion, ApplicationContext.currentQuestionState.getQuestion());
				}
				
			}
			
			public class GivenSecondNextQuestionRequest{
				
				@Before
				public void givenSecondNextQuestionRequest(){
					navigateToFirstQuestion();
					setMockRequestOffset(1);
				}
				@Test
				public void canNavigateToEnd() {
					useCase.execute(mockRequest);
					assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
							is(instanceOf(EndPrompt.class)));
				}
				
			}
			
			public class GivenFormIsAtTheEnd{
				
				@Before
				public void givenFormIsAtTheEnd(){
					navigateToFirstQuestion();
					setMockRequestOffset(1);
					useCase.execute(mockRequest);
				}
				@Test
				public void gettingPrevQuestionGetsGivenQuestion(){
					setMockRequestOffset(-1);
					useCase.execute(mockRequest);
					assertEquals(mockQuestion, ApplicationContext.currentQuestionState.getQuestion());
				}
				
			}
		}
		
		public class GivenAnswerIsRequired{
			private PresentableResponseImpl presentedNavigation;
			
			private PresentableResponseImpl updatePresentedNavigation() {
				return presentedNavigation = (PresentableResponseImpl)
						ApplicationContext.navigationPresenter.getPresentableResponse();
			}
			
			@Before
			public void givenAnswerIsRequired(){
				mockQuestion = MockCreation.makeMockAgeQuestion();
				ApplicationContext.questionGateway.save(mockQuestion);
			}
			
			public class GivenFirstNextQuestionRequest{
				
				@Before
				public void givenFirstNextQuestionRequest(){
					setMockRequestOffset(1);
				}
				@Test
				public void canNavigateToGivenQuestion() {
					useCase.execute(mockRequest);
					assertEquals(mockQuestion, ApplicationContext.currentQuestionState.getQuestion());
				}
				
			}		

			public class GivenSecondNextQuestionRequest{

				private String getFailedNavigationResult(){
					return "Sorry, you cannot move ahead.  The current question requires a response.";
				}
				
				@Before
				public void givenSecondNextQuestionRequest(){
					navigateToFirstQuestion();
					setMockRequestOffset(1);
				}
				@Test
				public void cannotNavigateToEnd() {
					useCase.execute(mockRequest);
					updatePresentedNavigation();
					
					assertThat(presentedNavigation.getOutcome(), is(ActionOutcome.FAILED));
					assertEquals(getFailedNavigationResult(), presentedNavigation.getMessage());
					assertEquals(mockQuestion, ApplicationContext.currentQuestionState.getQuestion());
				}
				
			}
			
			public class GivenCurrentQuestionRequest {
				
				@Before
				public void givenCurrentQuestionRequest(){
					navigateToFirstQuestion();
					setMockRequestOffset(0);
				}
				@Test
				public void canRepeatQuestion() {
					useCase.execute(mockRequest);
					updatePresentedNavigation();
					
					assertThat(presentedNavigation.getOutcome(), is(ActionOutcome.SUCCEEDED));
					assertEquals(mockQuestion, ApplicationContext.currentQuestionState.getQuestion());
				}
				
			}
			
			public class GivenPrevQuestionRequest {
				
				@Before
				public void givenPrevQuestionRequest(){
					navigateToFirstQuestion();
					setMockRequestOffset(-1);
				}
				@Test
				public void canGoBackToStart() {
					setMockRequestOffset(-1);
					useCase.execute(mockRequest);
					assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
							is(instanceOf(StartPrompt.class)));
				}
				
			}
		}
	}
}
