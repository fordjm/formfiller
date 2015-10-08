package formfiller.delivery.router;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import formfiller.delivery.Controller;
import formfiller.delivery.controller.AddFormComponentController;
import formfiller.delivery.controller.AskQuestionController;
import formfiller.delivery.event.impl.ParsedEvent;
import formfiller.utilities.*;
//Adapted from:
//https://github.com/cleancoders/CleanCodeCaseStudy/blob/master/test/cleancoderscom/http/RouterTest.java
//Retrieved 2015-08-14

@RunWith(HierarchicalContextRunner.class)
public class RouterTest {
	private Router router;
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
	public class GivenAnAddUnstructuredFormComponentRequest {		
		@Before
		public void givenAnAddUnstructuredFormComponentRequest() {
			mockParsedRequest = 
					ParsedEventMocker.makeMockParsedEvent("addunstructuredformcomponent", "questionId");
			mockController = Mockito.mock(AddFormComponentController.class);
		}
		
		@Test
		public void canRouteAddUnstructuredFormComponentRequest() {
			router.addMethod("addunstructuredformcomponent", mockController);
			router.route(mockParsedRequest);

			verifyControllerHandledParsedRequest();
		}
	}
	
	public class GivenAnAskQuestionRequest {		
		@Before
		public void givenAnAskQuestionRequest() {
			mockParsedRequest = 
					ParsedEventMocker.makeMockParsedEvent("askquestion", "next");
			mockController = Mockito.mock(AskQuestionController.class);
		}
		
		@Test
		public void canRouteAskQuestionRequest() {
			router.addMethod("askquestion", mockController);
			router.route(mockParsedRequest);

			verifyControllerHandledParsedRequest();
		}
	}
}
