package formfiller.usecases;

import formfiller.boundaries.UseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundControllerUseCase;
import formfiller.usecases.navigation.NavigationUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {

	public UseCase make(String useCaseName) {
		if (useCaseName.equalsIgnoreCase("handleUnfoundController"))
			return makehandleUnfoundControllerUseCase();
		else if (useCaseName.equalsIgnoreCase("navigation"))
			return makeNavigationUseCase();
		else
			throw new UnknownUseCase();
	}
	
	private UseCase makehandleUnfoundControllerUseCase() {
		return new HandleUnfoundControllerUseCase();
	}

	private UseCase makeNavigationUseCase() {
		return new NavigationUseCase();
	}
	
	public class UnknownUseCase extends RuntimeException{ }

}
