package formfiller.usecases;

public class NavigationController {

	public void requestNavigation(int offset) {
		NavigationUseCase useCase = new NavigationUseCase();
		RequestNavigation.navigationRequest.setOffset(offset);
		useCase.requestNavigation();
		
		PresentQuestionController pqc = new PresentQuestionController();
		pqc.requestPresentableQuestion();
	}
}
