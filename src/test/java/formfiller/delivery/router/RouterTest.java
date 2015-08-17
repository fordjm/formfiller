package formfiller.delivery.router;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.delivery.Controller;
import formfiller.delivery.userRequestParser.ParsedUserRequest;
//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RouterTest.java
//Retrieved 2015-08-14
@RunWith(HierarchicalContextRunner.class)
public class RouterTest {
	Router router;
	private ParsedUserRequest mockParsedRequest;
	private Controller mockController;
	
	@Before
	public void setUp(){
		router = new Router();
	}

	private void verifyControllerHandledParsedRequest() {
		Mockito.verify(mockController).handle(mockParsedRequest);
	}

	// TODO:  public class GivenAnEmptyStringRequest{ ... }
	//		  Should route() return a String with routing info?
	public class GivenAPresentQuestionRequest{
		@Before
		public void givenAPresentQuestionRequest(){
			mockParsedRequest = RouterTestHelper.makeMockParsedRequest("presentQuestion");
			mockController = RouterTestHelper.makeMockPresentQuestionController();
		}
		@Test
		public void canRoutePresentQuestionRequest() {
			router.addMethod("presentQuestion", mockController);
			router.route(mockParsedRequest);
			
			verifyControllerHandledParsedRequest();
		}
	}
	public class GivenANavigationRequest{
		@Before
		public void givenANavigationRequest(){
			mockParsedRequest = RouterTestHelper.makeMockParsedRequest("navigation", "1");
			mockController = RouterTestHelper.makeMockNavigationController();
		}
		@Test
		public void canRouteNavigationRequest() {
			router.addMethod("navigation", mockController);
			router.route(mockParsedRequest);

			verifyControllerHandledParsedRequest();
		}
	}

}
