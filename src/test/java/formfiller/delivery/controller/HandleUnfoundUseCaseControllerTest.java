package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.FormFillerContext;
import formfiller.delivery.event.ParsedEvent;
import formfiller.enums.Outcome;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.*;

public class HandleUnfoundUseCaseControllerTest {
	private HandleUnfoundUseCaseController controller;
	private ParsedEvent parsedEvent;
	
	private PresentableResponse getPresentableUnfoundUseCaseResponse(){
		PresentableResponse result = 
				FormFillerContext.responsePresenter.getPresentableResponse();
		return result;
	}
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		controller = new HandleUnfoundUseCaseController();
	}
	
	@Test
	public void canHandleNull() {
		controller.handle(null);
	}
	
	@Test
	public void canHandleEmptyStringEvent() {	
		parsedEvent = ParsedEventMocker.makeMockParsedEvent("");
		
		controller.handle(parsedEvent);
		
		assertThat(getPresentableUnfoundUseCaseResponse().outcome, 
				is(Outcome.NEGATIVE));
		assertThat(getPresentableUnfoundUseCaseResponse().message, 
				is("Request was not found."));
	}
	
	@Test
	public void canHandleParsedEvent() {	
		parsedEvent = ParsedEventMocker.makeMockParsedEvent("unknown none");
		
		controller.handle(parsedEvent);
		
		assertThat(getPresentableUnfoundUseCaseResponse().outcome, 
				is(Outcome.NEGATIVE));
		assertThat(getPresentableUnfoundUseCaseResponse().message, 
				is("Request unknown none was not found."));
	}
}
