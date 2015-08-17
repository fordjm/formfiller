package formfiller.usecases;

import formfiller.boundaries.UseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundControllerUseCase;
import formfiller.usecases.navigation.NavigationUseCase;
import formfiller.usecases.presentQuestion.PresentQuestionUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {

	public UseCase make(String useCaseName) {
		if (useCaseName.equalsIgnoreCase("handleUnfoundController"))
			return makehandleUnfoundControllerUseCase();
		else if (useCaseName.equalsIgnoreCase("presentQuestion"))
			return makePresentQuestionUseCase();
		else if (useCaseName.equalsIgnoreCase("navigation"))
			return makeNavigationUseCase();
		else
			throw new UnknownUseCase();
	}
	private UseCase makehandleUnfoundControllerUseCase() {
		return new HandleUnfoundControllerUseCase();
	}
	private UseCase makePresentQuestionUseCase() {
		return new PresentQuestionUseCase();
	}
	private UseCase makeNavigationUseCase() {
		return new NavigationUseCase();
	}
	
	public class UnknownUseCase extends RuntimeException{ }

}
