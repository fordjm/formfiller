package formfiller.delivery.router;

import org.mockito.Mockito;

import formfiller.delivery.Controller;
import formfiller.delivery.controller.HandleUnfoundController;
import formfiller.delivery.controller.NavigationController;
import formfiller.delivery.controller.PresentQuestionController;
import formfiller.delivery.userRequestParser.ParsedUserRequest;

public class RouterTestHelper {
	
	public static ParsedUserRequest makeMockParsedRequest(String method){
		return makeMockParsedRequest(method, "");
	}
	public static ParsedUserRequest makeMockParsedRequest(String method, String param){
		ParsedUserRequest result = Mockito.mock(ParsedUserRequest.class);
		Mockito.when(result.getMethod()).thenReturn(method);
		Mockito.when(result.getParam()).thenReturn(param);
		return result;
	}
	public static Controller makeMockPresentQuestionController(){
		return Mockito.mock(PresentQuestionController.class);
	}
	public static Controller makeMockNavigationController(){
		return Mockito.mock(NavigationController.class);
	}
	public static Controller makeMockNotFoundController() {
		return Mockito.mock(HandleUnfoundController.class);
	}
	
}
