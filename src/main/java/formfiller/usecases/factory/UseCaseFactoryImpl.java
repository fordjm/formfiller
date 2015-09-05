package formfiller.usecases.factory;

import formfiller.appBoundaries.UseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundUseCaseUseCase;
import formfiller.usecases.navigation.NavigationUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {

	public UseCase make(String useCaseName) {
		if (useCaseName == null) useCaseName = "";
			
		if (useCaseName.equalsIgnoreCase("navigation"))
			return makeNavigationUseCase();
		else
			return makehandleUnfoundUseCaseUseCase();
	}
	
	private UseCase makehandleUnfoundUseCaseUseCase() {
		return new HandleUnfoundUseCaseUseCase();
	}

	private UseCase makeNavigationUseCase() {
		return new NavigationUseCase();
	}
}
