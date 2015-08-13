package formfiller.usecases;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.entities.Question;
import formfiller.entities.StartPrompt;
import formfiller.gateways.ApplicationContext;
import formfiller.utilities.TestSetup;
import formfiller.utilities.TestUtil;

public class NavigationControllerTest {
	private NavigationController navigationController;
	private Question mockNameQuestion;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		navigationController = new NavigationController();
		mockNameQuestion = TestUtil.makeMockNameQuestion();
		ApplicationContext.questionGateway.save(mockNameQuestion);
	}
	@Test
	public void requestingPrevQuestionReturnsStartPrompt() {
		navigationController.requestNavigation(-1);
		assertThat(ApplicationContext.questionGateway.getQuestion(), 
				is(instanceOf(StartPrompt.class)));
	}
	@Test
	public void requestingCurrentQuestionReturnsStartPrompt() {
		navigationController.requestNavigation(0);
		assertThat(ApplicationContext.questionGateway.getQuestion(), 
				is(instanceOf(StartPrompt.class)));
	}
	@Test
	public void requestingNextQuestionReturnsGivenQuestion() {
		navigationController.requestNavigation(1);
		assertEquals(mockNameQuestion, ApplicationContext.questionGateway.getQuestion());
	}

}
