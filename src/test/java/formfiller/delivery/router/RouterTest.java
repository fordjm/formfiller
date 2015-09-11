package formfiller.delivery.router;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.delivery.Controller;
import formfiller.delivery.event.ParsedEvent;
import formfiller.utilities.*;
//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RouterTest.java
//Retrieved 2015-08-14

@RunWith(HierarchicalContextRunner.class)
public class RouterTest {
	Router router;
	private ParsedEvent mockParsedRequest;
	private Controller mockController;

	private void verifyControllerHandledParsedRequest() {
		Mockito.verify(mockController).handle(mockParsedRequest);
	}
	
	@Before
	public void setUp() {
		router = PlaceholderRouterFactory.makeRouter();
	}
	
	@Test
	public void canHandleNull() {
		router.route(null);
	}

	// TODO:  public class GivenAnEmptyStringRequest{ ... }
	//		  Should route() return a String with routing info?
	public class GivenAnAskQuestionRequest {
		
		@Before
		public void givenAnAskQuestionRequest() {
			mockParsedRequest = 
					ParsedEventMocker.makeMockParsedEvent("askquestion", "next");
			mockController = ControllerMocker.makeMockAskQuestionController();
		}
		
		@Test
		public void canRouteAskQuestionRequest() {
			router.addMethod("askquestion", mockController);
			router.route(mockParsedRequest);

			verifyControllerHandledParsedRequest();
		}
	}
}
