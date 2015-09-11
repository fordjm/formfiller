package formfiller.usecases.factory;

import formfiller.appBoundaries.UseCase;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundUseCaseUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {

	public UseCase make(String useCaseName) {
		if (useCaseName == null) useCaseName = "";
			
		if (useCaseName.equalsIgnoreCase("askQuestion"))
			return makeAskQuestionUseCase();
		else
			return makehandleUnfoundUseCaseUseCase();
	}
	
	private UseCase makehandleUnfoundUseCaseUseCase() {
		return new HandleUnfoundUseCaseUseCase();
	}

	private UseCase makeAskQuestionUseCase() {
		return new AskQuestionUseCase();
	}
}
