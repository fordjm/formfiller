package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.AnswerImpl;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.entities.Question;
import formfiller.enums.ActionOutcome;
import formfiller.request.interfaces.NavigationRequest;
import formfiller.usecases.navigation.NavigationUseCase;
import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableResponseImpl;
import formfiller.utilities.*;

@RunWith(HierarchicalContextRunner.class)
public class NavigationTest {	
	private NavigationUseCase navigationUseCase;
	private NavigationRequest mockRequest;
	private FormComponent foundFormComponent;
	
	// TODO:	Change index to Navigation.Direction
	void setMockRequestOffset(int offset){
		when(mockRequest.getOffset()).thenReturn(offset);
	}
	
	private Prompt findQuestionByIndex(int index) {
		return ApplicationContext.questionGateway.findQuestionByIndex(index);
	}
	
	private FormComponent findFormComponentByIndex(int index){
		return ApplicationContext.formComponentGateway.findByIndex(index);
	}
	
	@Before
	public void setUp(){
		TestSetup.setupContext();
		navigationUseCase = new NavigationUseCase();
		mockRequest = mock(NavigationRequest.class);		
	}
	
	@Test(expected = NavigationUseCase.NullExecution.class)
	public void cannotExecuteNull() {
		navigationUseCase.execute(null);
	}
	
	private FormComponent getCurrentFormComponent() {
		return ApplicationContext.formComponentGateway.navigator.getCurrent();
	}

	public class GivenNoQuestions{
		
		public class GivenPrevQuestionRequest{
			
			@Before
			public void givenPrevQuestionRequest(){
				setMockRequestOffset(-1);
			}
			@Test
			public void gettingQuestionGetsStartPrompt(){			
				foundFormComponent = findFormComponentByIndex(-1);
				
				navigationUseCase.execute(mockRequest);
				
				assertEquals(navigationUseCase, ApplicationContext.executedUseCases.peek().getUseCase());
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
			}
		}
		
		public class GivenCurrentQuestionRequest{
			
			@Before
			public void givenCurrentQuestionRequest(){
				setMockRequestOffset(0);
			}
			@Test
			public void gettingQuestionGetsStartPrompt(){
				foundFormComponent = findFormComponentByIndex(0);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
			}
			
		}
		
		public class GivenNextQuestionRequest{
			
			@Before
			public void givenNextQuestionRequest(){
				setMockRequestOffset(1);
			}
			@Test
			public void gettingQuestionGetsEndPrompt(){
				foundFormComponent = findFormComponentByIndex(0);
				
				navigationUseCase.execute(mockRequest);
				
				assertThat(getCurrentFormComponent(), is(foundFormComponent));
			}
			
		}
	}
	
	public class GivenOneQuestion{
		Question mockQuestion;
		FormComponent mockFormComponent;
		
		private FormComponent makeMockFormComponent(Prompt question){
			return FormComponentMocker.makeMockFormComponent(question, AnswerImpl.NONE);
		}
		
		public class GivenAnswerNotRequired{
			
			@Before
			public void givenAnswerNotRequired(){
				mockQuestion = QuestionMocker.makeMockNameQuestion();
				mockFormComponent = makeMockFormComponent(mockQuestion);
				ApplicationContext.questionGateway.save(mockQuestion);	//
				ApplicationContext.formComponentGateway.save(mockFormComponent);
			}
			@Test
			public void questionExistsAtIndexZero(){
				assertEquals(mockQuestion, findQuestionByIndex(0));	//
				assertEquals(mockFormComponent, findFormComponentByIndex(0));
			}
			
			public class GivenANextQuestionRequest{
				
				@Before
				public void givenNextQuestionRequest(){
					setMockRequestOffset(1);
				}
				@Test
				public void canNavigateToFirstQuestion() {
					foundFormComponent = findFormComponentByIndex(1);
					
					navigationUseCase.execute(mockRequest);
					
					assertThat(getCurrentFormComponent(), is(foundFormComponent));
				}
				
			}
			
			public class GivenFormIsAtTheEnd{
				
				@Before
				public void givenFormIsAtTheEnd(){
					setMockRequestOffset(1);
					navigationUseCase.execute(mockRequest);
				}
				@Test
				public void gettingPrevQuestionGetsGivenQuestion(){
					foundFormComponent = findFormComponentByIndex(0);
					setMockRequestOffset(-1);
					
					navigationUseCase.execute(mockRequest);
					
					assertThat(getCurrentFormComponent(), is(foundFormComponent));
				}
				
			}
		}
		
		public class GivenAnswerIsRequired{
			
			//	TODO:	Don't test PresentedNavigation.  Test ExecutedUseCase.
			private PresentableResponseImpl getPresentedNavigation() {
				return (PresentableResponseImpl)
						ApplicationContext.navigationPresenter.getPresentableResponse();
			}
			
			@Before
			public void givenAnswerIsRequired(){
				mockQuestion = QuestionMocker.makeMockAgeQuestion();
				mockFormComponent = makeMockFormComponent(mockQuestion);
				ApplicationContext.formComponentGateway.save(mockFormComponent);
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
					foundFormComponent = findFormComponentByIndex(0);
					
					navigationUseCase.execute(mockRequest);
					
					assertThat(getPresentedNavigation().getOutcome(), is(ActionOutcome.FAILED));
					assertEquals(getFailedNavigationResult(), getPresentedNavigation().getMessage());
					assertThat(getCurrentFormComponent(), is(foundFormComponent));
				}
				
			}
			
			public class GivenCurrentQuestionRequest {
				
				@Before
				public void givenCurrentQuestionRequest(){
					setMockRequestOffset(0);
				}
				@Test
				public void canRepeatQuestion() {
					foundFormComponent = findFormComponentByIndex(0);
					
					navigationUseCase.execute(mockRequest);
					
					assertThat(getPresentedNavigation().getOutcome(), is(ActionOutcome.SUCCEEDED));	//
					assertThat(getCurrentFormComponent(), is(foundFormComponent));
				}
				
			}
			
			public class GivenPrevQuestionRequest {
				
				@Before
				public void givenPrevQuestionRequest(){
					setMockRequestOffset(-1);
				}
				@Test
				public void canGoBackToStart() {
					foundFormComponent = findFormComponentByIndex(-1);
					setMockRequestOffset(-1);
					
					navigationUseCase.execute(mockRequest);
					
					assertThat(getCurrentFormComponent(), is(foundFormComponent));
				}
				
			}
		}
	}
}
