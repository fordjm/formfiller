package formfiller.delivery.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableHandleUnfoundController;
import formfiller.delivery.router.RouterTestHelper;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
import formfiller.enums.ActionOutcome;
import formfiller.utilities.TestSetup;

public class HandleUnfoundControllerTest {

	private HandleUnfoundController controller;
	private ParsedUserRequest request;
	
	private PresentableHandleUnfoundController getPresentableUnfoundControllerResponse(){
		PresentableHandleUnfoundController result = 
				ApplicationContext.handleUnfoundControllerPresenter.getPresentableResponse();
		return result;
	}
	
	@Before
	public void setUp() {
		TestSetup.setupContext();
		controller = new HandleUnfoundController();
		request = RouterTestHelper.makeMockParsedRequest("");
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
