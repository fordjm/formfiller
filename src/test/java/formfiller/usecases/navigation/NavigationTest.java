package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
import formfiller.utilities.TestUtil;

@RunWith(HierarchicalContextRunner.class)
public class NavigationTest {	
	private NavigationUseCase useCase;
	
	@Before
	public void setUp(){
		TestSetup.setupContext();
		useCase = new NavigationUseCase();
	}
	
	public class GivenNoQuestions{
		@Test
		public void gettingPrevQuestionGetsStartPrompt(){
			useCase.navigateToPrevQuestion();
			assertThat(ApplicationContext.questionGateway.getQuestion(), 
					is(instanceOf(StartPrompt.class)));
		}
		@Test
		public void gettingCurrentQuestionGetsStartPrompt(){
			useCase.navigateToCurrentQuestion();
			assertThat(ApplicationContext.questionGateway.getQuestion(), 
					is(instanceOf(StartPrompt.class)));
		}
		@Test
		public void gettingNextQuestionGetsEndPrompt(){
			useCase.navigateToNextQuestion();
			assertThat(ApplicationContext.questionGateway.getQuestion(), 
					is(instanceOf(EndPrompt.class)));
		}
	}
	public class GivenOneQuestion{
		Question question;
		
		public void navigateToNextQuestion(int repetitions){
			for (int i=0; i<repetitions; ++i)
				useCase.navigateToNextQuestion();
		}
		
		public class GivenAnswerNotRequired{
			@Before
			public void givenAnswerNotRequired(){
				question = TestUtil.makeMockNameQuestion();
				ApplicationContext.questionGateway.save(question);
			}
			@Test
			public void canNavigateToFirstQuestion() {
				navigateToNextQuestion(1);
				assertEquals(question, ApplicationContext.questionGateway.getQuestion());
			}
			@Test
			public void canNavigateToEnd() {
				navigateToNextQuestion(2);
				assertThat(ApplicationContext.questionGateway.getQuestion(), 
						is(instanceOf(EndPrompt.class)));
			}
			public class GivenFormIsAtTheEnd{
				@Before
				public void givenFormIsAtTheEnd(){
					navigateToNextQuestion(2);
				}
				@Test
				public void gettingPrevQuestionGetsFirstQuestion(){
					useCase.navigateToPrevQuestion();
					assertEquals(question, ApplicationContext.questionGateway.getQuestion());
				}
			}
		}
		public class GivenAnswerIsRequired{
			@Before
			public void givenAnswerIsRequired(){
				question = TestUtil.makeMockAgeQuestion();
				ApplicationContext.questionGateway.save(question);
			}
			@Test
			public void canNavigateToFirstQuestion() {
				navigateToNextQuestion(1);
				assertEquals(question, ApplicationContext.questionGateway.getQuestion());
			}
			@Test(expected=NavigationUseCase.ResponseRequired.class)
			public void cannotNavigateToEnd() {
				navigateToNextQuestion(2);
			}
			
		}
	}
}
