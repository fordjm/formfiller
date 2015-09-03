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

public class HandleUnfoundControllerControllerTest {
	private HandleUnfoundControllerController controller;
	private ParsedEvent parsedEvent;
	
	private PresentableResponse getPresentableUnfoundControllerResponse(){
		PresentableResponse result = 
				ApplicationContext.failedUseCasePresenter.getPresentableResponse();
		return result;
	}
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		controller = new HandleUnfoundControllerController();
		parsedEvent = ParsedEventMocker.makeMockParsedEvent("");
	}
	
	@Test
	public void canHandle() {		
		controller.handle(parsedEvent);
		
		assertThat(getPresentableUnfoundControllerResponse().outcome, 
				is(ActionOutcome.FAILED));
		assertThat(getPresentableUnfoundControllerResponse().message, 
				is("Request was not found."));
	}
}
