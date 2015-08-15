package formfiller.usecases;

import formfiller.usecases.navigation.NavigationUseCase;
import formfiller.usecases.presentQuestion.PresentQuestionUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {

	public UseCase make(String useCaseName) {
		switch(useCaseName){
		case "presentQuestion": 
			return makePresentQuestionUseCase();
		case "navigation": 
			return makeNavigationUseCase();
		default:
			return null;	// TODO:  Return NoUseCase object?  Throw exception?
		}
	}

	private UseCase makePresentQuestionUseCase() {
		return new PresentQuestionUseCase();
	}

	private UseCase makeNavigationUseCase() {
		return new NavigationUseCase();
	}

}
