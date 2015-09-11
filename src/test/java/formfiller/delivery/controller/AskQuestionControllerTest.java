package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import formfiller.FormFillerContext;
import formfiller.delivery.event.ParsedEvent;
import formfiller.entities.formComponent.FormComponent;
import formfiller.gateways.FormComponentGateway;
import formfiller.gateways.InMemoryFormComponentGateway;
import formfiller.utilities.*;

public class AskQuestionControllerTest {
	private AskQuestionController askQuestionController;
	private ParsedEvent mockParsedUserRequest;
	private FormComponent formComponentFoundAtIndex;

	//	TODO:	Break index dependency and remove below 3 functions
	private void updateFormComponentFoundAtIndex(int index) {
		formComponentFoundAtIndex = findFormComponentByIndex(index);
	}

	private FormComponent findFormComponentByIndex(int index) {
		return getInMemoryFormComponentGateway().findByIndex(index);
	}
	
	private InMemoryFormComponentGateway getInMemoryFormComponentGateway(){
		InMemoryFormComponentGateway result = (InMemoryFormComponentGateway)
				getFormComponentGatewayFromContext();		
		return result;
	}

	private FormComponentGateway getFormComponentGatewayFromContext() {
		return FormFillerContext.formComponentGateway;
	}
	
	private FormComponent makeMockNameFormComponent() {
		FormComponent result = Mockito.mock(FormComponent.class);
		result.id = "name";
		result.question = QuestionMocker.makeMockNameQuestion();
		result.answer = AnswerMocker.makeMockAnswer(0, "myName");
		return result;
	}
	
	private FormComponent getCurrentFormComponent() {
		return FormFillerContext.formComponentState.getCurrent();
	}
	
	@Before
	public void setupTest(){
		TestSetup.setupContext();
		getFormComponentGatewayFromContext().save(makeMockNameFormComponent());
		askQuestionController = new AskQuestionController();
	}

	@Test
	public void movingBackward_ReturnsStartComponent() {
		updateFormComponentFoundAtIndex(-1);
		mockParsedUserRequest = 
				ParsedEventMocker.makeMockParsedEvent("askQuestion", "backward");
		
		askQuestionController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(formComponentFoundAtIndex));
		assertThat(getCurrentFormComponent().id, is("start"));
	}

	@Test
	public void requestingCurrentQuestionReturnsStartPrompt() {
		updateFormComponentFoundAtIndex(0);
		mockParsedUserRequest = 
				ParsedEventMocker.makeMockParsedEvent("askQuestion", "none");
		
		askQuestionController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(formComponentFoundAtIndex));
		assertThat(getCurrentFormComponent().id, is("name"));
	}
	
	@Test
	public void requestingNextQuestionReturnsGivenQuestion() {
		updateFormComponentFoundAtIndex(1);
		mockParsedUserRequest = 
				ParsedEventMocker.makeMockParsedEvent("askQuestion", "forward");
		
		askQuestionController.handle(mockParsedUserRequest);
		
		assertThat(getCurrentFormComponent(), is(formComponentFoundAtIndex));
		assertThat(getCurrentFormComponent().id, is("end"));
	}
}