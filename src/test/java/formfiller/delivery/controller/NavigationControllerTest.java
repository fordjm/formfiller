package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.ApplicationContext;
import formfiller.delivery.eventParser.ParsedEvent;
import formfiller.entities.FormComponent;
import formfiller.gateways.InMemoryFormComponentGateway;
import formfiller.utilities.*;

public class NavigationControllerTest {
	private NavigationController navigationController;
	private ParsedEvent mockParsedUserRequest;
	private FormComponent formComponentFoundAtIndex;
	FormComponent currentComponent;

	private void updateFormComponentFoundAtIndex(int index) {
		formComponentFoundAtIndex = findFormComponentByIndex(index);
	}
	
	private InMemoryFormComponentGateway getInMemoryFormComponentGateway(){
		InMemoryFormComponentGateway result = (InMemoryFormComponentGateway)
				ApplicationContext.formComponentGateway;		
		return result;
	}

	private FormComponent findFormComponentByIndex(int index) {
		return getInMemoryFormComponentGateway().findByIndex(index);
	}
	
	private FormComponent getCurrentFormComponent() {
		return getInMemoryFormComponentGateway().transporter.getCurrent();
	}
	
	private FormComponent makeMockFormComponent() {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = "name";
		result.question = QuestionMocker.makeMockNameQuestion();
		result.answer = AnswerMocker.makeMockNameAnswer("myName");
		return result;
	}
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		ApplicationContext.formComponentGateway.save(makeMockFormComponent());
		navigationController = new NavigationController();
	}

	@Test
	public void movingBackward_ReturnsStartComponent() {
		updateFormComponentFoundAtIndex(-1);
		mockParsedUserRequest = 
				ParsedEventMocker.makeMockParsedEvent("navigation", "backward");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(formComponentFoundAtIndex));
		assertThat(getCurrentFormComponent().id, is("start"));
	}

	@Test
	public void requestingCurrentQuestionReturnsStartPrompt() {
		updateFormComponentFoundAtIndex(0);
		mockParsedUserRequest = 
				ParsedEventMocker.makeMockParsedEvent("navigation", "none");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(formComponentFoundAtIndex));
		assertThat(getCurrentFormComponent().id, is("name"));
	}
	
	@Test
	public void requestingNextQuestionReturnsGivenQuestion() {
		updateFormComponentFoundAtIndex(1);
		mockParsedUserRequest = 
				ParsedEventMocker.makeMockParsedEvent("navigation", "forward");
		
		navigationController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(formComponentFoundAtIndex));
		assertThat(getCurrentFormComponent().id, is("end"));
	}

}
