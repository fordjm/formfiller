package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.delivery.Controller;
import formfiller.delivery.controller.HandleUnfoundControllerController;
import formfiller.delivery.controller.NavigationController;
import formfiller.delivery.controller.PresentQuestionController;

public class ControllerMocker {
	
	public static Controller makeMockPresentQuestionController(){
		return Mockito.mock(PresentQuestionController.class);
	}
	
	public static Controller makeMockNavigationController(){
		return Mockito.mock(NavigationController.class);
	}
	
	public static Controller makeMockHandleUnfoundControllerController() {
		return Mockito.mock(HandleUnfoundControllerController.class);
	}	
}
