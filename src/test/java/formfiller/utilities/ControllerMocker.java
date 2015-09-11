package formfiller.utilities;

import org.mockito.Mockito;

import formfiller.delivery.Controller;
import formfiller.delivery.controller.AskQuestionController;

public class ControllerMocker {
	
	public static Controller makeMockAskQuestionController(){
		return Mockito.mock(AskQuestionController.class);
	}
}
