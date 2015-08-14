package formfiller.usecases.navigation;

import formfiller.ui.Controller;
import formfiller.ui.consoleUi.ParsedUserRequest;
import formfiller.usecases.presentQuestion.PresentQuestionController;

public class NavigationController implements Controller {

	public void handle(ParsedUserRequest parsedInput) {
		int offset = Integer.parseInt(parsedInput.getParam());
		NavigationRequest navigationRequest = makeNavigationRequest(offset);
		NavigationUseCase useCase = new NavigationUseCase();
		useCase.requestNavigation(navigationRequest);
		
		PresentQuestionController pqc = new PresentQuestionController();
		pqc.handle(parsedInput);
	}
	
	public NavigationRequest makeNavigationRequest(int offset){
		NavigationRequestFactory factory = new NavigationRequestFactoryImpl();
		NavigationRequest result = factory.makeNavigationRequest();
		result.setOffset(offset);
		return result;
	}
}
