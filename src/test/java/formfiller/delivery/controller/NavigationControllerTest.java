package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.delivery.router.RouterTestHelper;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.entities.Prompt;
import formfiller.utilities.MockCreation;
import formfiller.utilities.TestSetup;

public class NavigationControllerTest {
	private NavigationController navigationController;
	private ParsedUserRequest mockParsedUserRequest;
	private Prompt foundQuestion;
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		navigationController = new NavigationController();
		ApplicationContext.questionGateway.save(MockCreation.makeMockNameQuestion());
	}
	@Test
	public void requestingPrevQuestionReturnsStartPrompt() {
		foundQuestion = ApplicationContext.questionGateway.findQuestionByIndex(-1);		
		mockParsedUserRequest = RouterTestHelper.makeMockParsedRequest("navigation", "-1");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
				is(foundQuestion));
	}
	@Test
	public void requestingCurrentQuestionReturnsStartPrompt() {
		foundQuestion = ApplicationContext.questionGateway.findQuestionByIndex(0);
		mockParsedUserRequest = RouterTestHelper.makeMockParsedRequest("navigation", "0");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
				is(foundQuestion));
	}
	@Test
	public void requestingNextQuestionReturnsGivenQuestion() {
		foundQuestion = ApplicationContext.questionGateway.findQuestionByIndex(1);
		mockParsedUserRequest = RouterTestHelper.makeMockParsedRequest("navigation", "1");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(ApplicationContext.currentQuestionState.getQuestion(), 
				is(foundQuestion));
	}

}
