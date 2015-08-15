package formfiller.usecases.navigation;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.entities.Question;
import formfiller.entities.StartPrompt;
import formfiller.ui.router.RouterTestHelper;
import formfiller.ui.userRequestParser.ParsedUserRequest;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

public class NavigationControllerTest {
	private NavigationController navigationController;
	private ParsedUserRequest mockParsedUserRequest;
	private Question mockNameQuestion;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		navigationController = new NavigationController();
		mockNameQuestion = MockCreation.makeMockNameQuestion();
		ApplicationContext.questionGateway.save(mockNameQuestion);
	}
	@Test
	public void requestingPrevQuestionReturnsStartPrompt() {
		mockParsedUserRequest = RouterTestHelper.makeMockParsedInput("navigation", "-1");
		navigationController.handle(mockParsedUserRequest);
		assertThat(ApplicationContext.questionGateway.getQuestion(), 
				is(instanceOf(StartPrompt.class)));
	}
	@Test
	public void requestingCurrentQuestionReturnsStartPrompt() {
		mockParsedUserRequest = RouterTestHelper.makeMockParsedInput("navigation", "0");
		navigationController.handle(mockParsedUserRequest);
		assertThat(ApplicationContext.questionGateway.getQuestion(), 
				is(instanceOf(StartPrompt.class)));
	}
	@Test
	public void requestingNextQuestionReturnsGivenQuestion() {
		mockParsedUserRequest = RouterTestHelper.makeMockParsedInput("navigation", "1");
		navigationController.handle(mockParsedUserRequest);
		assertEquals(mockNameQuestion, ApplicationContext.questionGateway.getQuestion());
	}

}
