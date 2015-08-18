package formfiller.delivery.view;

import java.util.Observable;

import formfiller.ApplicationContext;
import formfiller.boundaryCrossers.PresentableHandleUnfoundController;
import formfiller.delivery.View;

public class ConsoleHandleUnfoundControllerView implements View {

	public void update(Observable o, Object arg) {
		displayPresentableResponse();
	}
	public void displayPresentableResponse() {
		PresentableHandleUnfoundController presentableHandleUnfoundController = 
				(PresentableHandleUnfoundController)
						ApplicationContext.handleUnfoundControllerPresenter.getPresentableResponse();
		System.out.println(presentableHandleUnfoundController.getMessage());
	}

}
