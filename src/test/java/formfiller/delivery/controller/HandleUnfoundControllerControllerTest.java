package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableResponse;
import formfiller.delivery.eventParser.ParsedEvent;
import formfiller.enums.ActionOutcome;
import formfiller.utilities.*;

public class HandleUnfoundControllerControllerTest {
	private HandleUnfoundControllerController controller;
	private ParsedEvent request;
	
	private PresentableResponse getPresentableUnfoundControllerResponse(){
		PresentableResponse result = 
				ApplicationContext.handleUnfoundControllerPresenter.getPresentableResponse();
		return result;
	}
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		controller = new HandleUnfoundControllerController();
		request = ParsedEventMocker.makeMockParsedEvent("");
	}
	
	@Test
	public void canHandle() {		
		controller.handle(request);
		
		assertThat(getPresentableUnfoundControllerResponse().getOutcome(), 
				is(ActionOutcome.FAILED));
		assertThat(getPresentableUnfoundControllerResponse().getMessage(), 
				is("Request was not found."));
	}
}
