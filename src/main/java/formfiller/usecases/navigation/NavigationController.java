package formfiller.usecases.navigation;

import formfiller.usecases.presentQuestion.PresentQuestionController;

public class NavigationController {

	public void requestNavigation(int offset) {
		NavigationRequest navigationRequest = makeNavigationRequest(offset);
		NavigationUseCase useCase = new NavigationUseCase();
		useCase.requestNavigation(navigationRequest);
		
		PresentQuestionController pqc = new PresentQuestionController();
		pqc.requestQuestionPresentation();
	}
	
	public NavigationRequest makeNavigationRequest(int offset){
		NavigationRequestFactory factory = new NavigationRequestFactoryImpl();
		NavigationRequest result = factory.makeNavigationRequest();
		result.setOffset(offset);
		return result;
	}
}
