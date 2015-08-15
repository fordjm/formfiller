package formfiller.ui.router;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.Controller;
import formfiller.ui.userRequestParser.ParsedUserRequest;
//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RouterTest.java
//Retrieved 2015-08-14
@RunWith(HierarchicalContextRunner.class)
public class RouterTest {
	Router router;
	private ParsedUserRequest mockParsedInput;
	private Controller controller;
	
	@Before
	public void setUp(){
		router = new Router();
	}

	public class GivenAPresentQuestionRequest{
		@Before
		public void givenAPresentQuestionRequest(){
			mockParsedInput = RouterTestHelper.makeMockParsedInput("presentQuestion");
			controller = RouterTestHelper.makeMockPresentQuestionController();
		}
		@Test
		public void canRoutePresentQuestionRequest() {
			router.addMethod("presentQuestion", controller);
			router.route(mockParsedInput);
			
			Mockito.verify(controller).handle(mockParsedInput);
		}
	}
	public class GivenANavigationRequest{
		@Before
		public void givenANavigationRequest(){
			mockParsedInput = RouterTestHelper.makeMockParsedInput("navigation", "1");
			controller = RouterTestHelper.makeMockNavigationController();
		}
		@Test
		public void canRouteNavigationRequest() {
			router.addMethod("navigation", controller);
			router.route(mockParsedInput);

			Mockito.verify(controller).handle(mockParsedInput);
		}
	}

}
