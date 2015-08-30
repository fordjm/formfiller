package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.delivery.Controller;
import formfiller.delivery.controller.HandleUnfoundControllerController;
import formfiller.delivery.controller.NavigationController;

public class ControllerMocker {
	
	public static Controller makeMockNavigationController(){
		return Mockito.mock(NavigationController.class);
	}
	
	public static Controller makeMockHandleUnfoundControllerController() {
		return Mockito.mock(HandleUnfoundControllerController.class);
	}	
}
