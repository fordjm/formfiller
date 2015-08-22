package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.Prompt;
import formfiller.entities.Question;
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
	private Prompt foundQuestion;
	
	void setMockRequestOffset(int offset){
		when(mockRequest.getOffset()).thenReturn(offset);
	}
	private Prompt findQuestionByIndex(int index) {
		return ApplicationContext.questionGateway.findQuestionByIndex(index);
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
				foundQuestion = findQuestionByIndex(-1);
				
				useCase.execute(mockRequest);
				
				assertEquals(useCase, ApplicationContext.executedUseCases.peek().getUseCase());
				assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
						is(foundQuestion));
			}
		}
		
		public class GivenCurrentQuestionRequest{
			
			@Before
			public void givenCurrentQuestionRequest(){
				setMockRequestOffset(0);
			}
			@Test
			public void gettingQuestionGetsStartPrompt(){
				foundQuestion = findQuestionByIndex(0);
				
				useCase.execute(mockRequest);
				
				assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
						is(foundQuestion));
			}
			
		}
		
		public class GivenNextQuestionRequest{
			
			@Before
			public void givenNextQuestionRequest(){
				setMockRequestOffset(1);
			}
			@Test
			public void gettingQuestionGetsEndPrompt(){
				foundQuestion = findQuestionByIndex(0);
				
				useCase.execute(mockRequest);
				
				assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
						is(foundQuestion));
			}
			
		}
	}
	
	public class GivenOneQuestion{
		Question mockQuestion;
		
		public class GivenAnswerNotRequired{
			
			@Before
			public void givenAnswerNotRequired(){
				mockQuestion = MockCreation.makeMockNameQuestion();
				ApplicationContext.questionGateway.save(mockQuestion);
			}
			@Test
			public void questionExistsAtIndexZero(){
				assertEquals(mockQuestion, findQuestionByIndex(0));
			}
			
			public class GivenANextQuestionRequest{
				
				@Before
				public void givenNextQuestionRequest(){
					setMockRequestOffset(1);
				}
				@Test
				public void canNavigateToFirstQuestion() {
					foundQuestion = findQuestionByIndex(1);
					
					useCase.execute(mockRequest);
					
					assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
							is(foundQuestion));
				}
				
			}
			
			public class GivenFormIsAtTheEnd{
				
				@Before
				public void givenFormIsAtTheEnd(){
					setMockRequestOffset(1);
					useCase.execute(mockRequest);
				}
				@Test
				public void gettingPrevQuestionGetsGivenQuestion(){
					foundQuestion = findQuestionByIndex(0);					
					setMockRequestOffset(-1);
					
					useCase.execute(mockRequest);
					
					assertEquals(foundQuestion, 
							ApplicationContext.currentQuestionState.getQuestion());
				}
				
			}
		}
		
		public class GivenAnswerIsRequired{
			
			private PresentableResponseImpl getPresentedNavigation() {
				return (PresentableResponseImpl)
						ApplicationContext.navigationPresenter.getPresentableResponse();
			}
			
			@Before
			public void givenAnswerIsRequired(){
				mockQuestion = MockCreation.makeMockAgeQuestion();
				ApplicationContext.questionGateway.save(mockQuestion);
			}	

			public class GivenANextQuestionRequest{

				private String getFailedNavigationResult(){
					return "Sorry, you cannot move ahead.  The current question requires a response.";
				}
				
				@Before
				public void givenANextQuestionRequest(){
					setMockRequestOffset(1);
				}
				@Test
				public void cannotNavigateToEnd() {
					foundQuestion = findQuestionByIndex(0);		
					
					useCase.execute(mockRequest);
					
					assertThat(getPresentedNavigation().getOutcome(), is(ActionOutcome.FAILED));
					assertEquals(getFailedNavigationResult(), getPresentedNavigation().getMessage());
					assertEquals(foundQuestion, ApplicationContext.currentQuestionState.getQuestion());
				}
				
			}
			
			public class GivenCurrentQuestionRequest {
				
				@Before
				public void givenCurrentQuestionRequest(){
					setMockRequestOffset(0);
				}
				@Test
				public void canRepeatQuestion() {
					foundQuestion = findQuestionByIndex(0);	
					
					useCase.execute(mockRequest);
					
					assertThat(getPresentedNavigation().getOutcome(), is(ActionOutcome.SUCCEEDED));
					assertEquals(foundQuestion, ApplicationContext.currentQuestionState.getQuestion());
				}
				
			}
			
			public class GivenPrevQuestionRequest {
				
				@Before
				public void givenPrevQuestionRequest(){
					setMockRequestOffset(-1);
				}
				@Test
				public void canGoBackToStart() {
					foundQuestion = findQuestionByIndex(-1);
					setMockRequestOffset(-1);
					
					useCase.execute(mockRequest);
					
					assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
							is(foundQuestion));
				}
				
			}
		}
	}
}
