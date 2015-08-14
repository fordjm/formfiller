package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.entities.EndPrompt;
import formfiller.entities.Question;
import formfiller.entities.StartPrompt;
import formfiller.usecases.navigation.NavigationUseCase;
import formfiller.ApplicationContext;
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
				useCase.requestNavigation(mockRequest);
				assertThat(ApplicationContext.questionGateway.getQuestion(), 
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
				useCase.requestNavigation(mockRequest);
				assertThat(ApplicationContext.questionGateway.getQuestion(), 
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
				useCase.requestNavigation(mockRequest);
				assertThat(ApplicationContext.questionGateway.getQuestion(), 
						is(instanceOf(EndPrompt.class)));
			}
		}
	}
	
	public class GivenOneQuestion{
		Question question;
		
		public void navigateByIndexOffsetLoop(int offset, int repetitions){
			setMockRequestOffset(offset);
			for (int i=0; i<repetitions; ++i)
				useCase.requestNavigation(mockRequest);
		}
		
		public class GivenAnswerNotRequired{
			@Before
			public void givenAnswerNotRequired(){
				question = MockCreation.makeMockNameQuestion();
				ApplicationContext.questionGateway.save(question);
			}
			@Test
			public void canNavigateToFirstQuestion() {
				navigateByIndexOffsetLoop(1, 1);
				assertEquals(question, ApplicationContext.questionGateway.getQuestion());
			}
			@Test
			public void canNavigateToEnd() {
				navigateByIndexOffsetLoop(1, 2);
				assertThat(ApplicationContext.questionGateway.getQuestion(), 
						is(instanceOf(EndPrompt.class)));
			}
			public class GivenFormIsAtTheEnd{
				@Before
				public void givenFormIsAtTheEnd(){
					navigateByIndexOffsetLoop(1, 2);
				}
				@Test
				public void gettingPrevQuestionGetsGivenQuestion(){
					navigateByIndexOffsetLoop(-1, 1);
					assertEquals(question, ApplicationContext.questionGateway.getQuestion());
				}
			}
		}
		
		public class GivenAnswerIsRequired{
			@Before
			public void givenAnswerIsRequired(){
				question = MockCreation.makeMockAgeQuestion();
				ApplicationContext.questionGateway.save(question);
			}
			@Test
			public void canNavigateToGivenQuestion() {
				navigateByIndexOffsetLoop(1, 1);
				assertEquals(question, ApplicationContext.questionGateway.getQuestion());
			}
			public class GivenCurrentQuestionRequiresAnswer{
				@Before
				public void givenCurrentQuestionRequiresAnswer(){
					navigateByIndexOffsetLoop(1, 1);
				}
				@Test(expected=NavigationUseCase.AnswerRequired.class)
				public void cannotNavigateToEnd() {
					navigateByIndexOffsetLoop(1, 1);
				}
				@Test
				public void canRepeatQuestion() {
					navigateByIndexOffsetLoop(0, 1);
					assertEquals(question, ApplicationContext.questionGateway.getQuestion());
				}
				@Test
				public void canGoBackToStart() {
					navigateByIndexOffsetLoop(-1, 1);
					assertThat(ApplicationContext.questionGateway.getQuestion(), 
							is(instanceOf(StartPrompt.class)));
				}
			}			
		}
	}
}
