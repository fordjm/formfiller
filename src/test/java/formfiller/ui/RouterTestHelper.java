package formfiller.ui;

import org.mockito.Mockito;

import formfiller.ui.consoleUi.ParsedUserRequest;
import formfiller.usecases.navigation.NavigationController;
import formfiller.usecases.presentQuestion.PresentQuestionController;

public class RouterTestHelper {
	
	public static ParsedUserRequest makeMockParsedInput(String method){
		return makeMockParsedInput(method, "");
	}
	public static ParsedUserRequest makeMockParsedInput(String method, String param){
		ParsedUserRequest result = Mockito.mock(ParsedUserRequest.class);
		Mockito.when(result.getMethod()).thenReturn(method);
		Mockito.when(result.getParam()).thenReturn(param);
		return result;
	}
	public static Controller makeMockPresentQuestionController(){
		Controller result = Mockito.mock(PresentQuestionController.class);		
		return result;
	}
	public static Controller makeMockNavigationController(){
		Controller result = Mockito.mock(NavigationController.class);		
		return result;
	}
	
}
