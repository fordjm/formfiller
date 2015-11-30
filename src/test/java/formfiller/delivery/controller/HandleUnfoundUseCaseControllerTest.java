package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.Context;
import formfiller.delivery.ViewModel;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.delivery.viewModel.NotificationViewModel;
import formfiller.enums.Outcome;
import formfiller.response.models.PresentableResponse;
import formfiller.utilities.*;

public class HandleUnfoundUseCaseControllerTest {
	private HandleUnfoundUseCaseController controller;
	private ParsedEvent parsedEvent;
	
	private NotificationViewModel getUnfoundUseCaseViewModel(){
		ViewModel result = 
				Context.outcomePresenter.getViewModel();
		NotificationViewModel castResult = (NotificationViewModel) result;
		return castResult;
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
		
		assertThat(getUnfoundUseCaseViewModel().message, 
				is("Request was not found."));
	}
	
	@Test
	public void canHandleParsedEvent() {	
		parsedEvent = ParsedEventMocker.makeMockParsedEvent("unknown none");
		
		controller.handle(parsedEvent);
		
		assertThat(getUnfoundUseCaseViewModel().message, 
				is("Request unknown none was not found."));
	}
}
