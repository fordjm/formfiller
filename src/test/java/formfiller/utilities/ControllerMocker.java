package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.delivery.Controller;
import formfiller.delivery.controller.NavigationController;

public class ControllerMocker {
	
	public static Controller makeMockNavigationController(){
		return Mockito.mock(NavigationController.class);
	}
}
