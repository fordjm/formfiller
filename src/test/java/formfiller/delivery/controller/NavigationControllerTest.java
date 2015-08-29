package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.entities.FormComponent;
import formfiller.utilities.*;

public class NavigationControllerTest {
	private NavigationController navigationController;
	private ParsedUserRequest mockParsedUserRequest;
	private FormComponent foundFormComponent;

	private FormComponent findFormComponentByIndex(int index) {
		return ApplicationContext.formComponentGateway.findByIndex(index);
	}
	
	private FormComponent getCurrentFormComponent() {
		return ApplicationContext.formComponentGateway.transporter.getCurrent();
	}
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		navigationController = new NavigationController();
		ApplicationContext.questionGateway.save(QuestionMocker.makeMockNameQuestion());
	}
	
	@Test
	public void requestingPrevQuestionReturnsStartPrompt() {
		foundFormComponent = findFormComponentByIndex(-1);
		mockParsedUserRequest = 
				ParsedUserRequestMocker.makeMockParsedUserRequest("navigation", "backward");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(foundFormComponent));
	}

	@Test
	public void requestingCurrentQuestionReturnsStartPrompt() {
		foundFormComponent = findFormComponentByIndex(0);
		mockParsedUserRequest = 
				ParsedUserRequestMocker.makeMockParsedUserRequest("navigation", "none");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(foundFormComponent));
	}
	
	@Test
	public void requestingNextQuestionReturnsGivenQuestion() {
		foundFormComponent = findFormComponentByIndex(1);
		mockParsedUserRequest = 
				ParsedUserRequestMocker.makeMockParsedUserRequest("navigation", "forward");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(foundFormComponent));
	}

}
