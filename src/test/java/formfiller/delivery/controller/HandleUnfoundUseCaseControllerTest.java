package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.delivery.event.ParsedEvent;
import formfiller.enums.ActionOutcome;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.*;

public class HandleUnfoundUseCaseControllerTest {
	private HandleUnfoundUseCaseController controller;
	private ParsedEvent parsedEvent;
	
	private PresentableResponse getPresentableUnfoundUseCaseResponse(){
		PresentableResponse result = 
				ApplicationContext.responsePresenter.getPresentableResponse();
		return result;
	}
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		controller = new HandleUnfoundUseCaseController();
		parsedEvent = ParsedEventMocker.makeMockParsedEvent("");
	}
	
	@Test
	public void canHandleNull() {
		controller.handle(null);
	}
	
	@Test
	public void canHandleParsedEvent() {		
		controller.handle(parsedEvent);
		
		assertThat(getPresentableUnfoundUseCaseResponse().outcome, 
				is(ActionOutcome.FAILED));
		assertThat(getPresentableUnfoundUseCaseResponse().message, 
				is("Request was not found."));
	}
}
