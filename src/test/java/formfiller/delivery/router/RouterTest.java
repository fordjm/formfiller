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
	
	@Before
	public void setUp(){
		router = Router.makeRouter();
	}

	private void verifyControllerHandledParsedRequest() {
		Mockito.verify(mockController).handle(mockParsedRequest);
	}

	// TODO:  public class GivenAnEmptyStringRequest{ ... }
	//		  Should route() return a String with routing info?
	public class GivenANavigationRequest{
		
		@Before
		public void givenANavigationRequest(){
			mockParsedRequest = 
					ParsedEventMocker.makeMockParsedEvent("navigation", "forward");
			mockController = ControllerMocker.makeMockNavigationController();
		}
		
		@Test
		public void canRouteNavigationRequest() {
			router.addMethod("navigation", mockController);
			router.route(mockParsedRequest);

			verifyControllerHandledParsedRequest();
		}
	}
}
