package formfiller.usecases.factory;

import formfiller.appBoundaries.UseCase;
import formfiller.usecases.addFormComponent.AddOptionVariableFormComponentUseCase;
import formfiller.usecases.addFormComponent.AddUnstructuredFormComponentUseCase;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundUseCaseUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {
	public UseCase make(String useCaseName) {
		if (useCaseName == null) useCaseName = "";
			
		if (useCaseName.equalsIgnoreCase("addUnstructuredFormComponent"))
			return makeAddUnstructuredFormComponentUseCase();
		else if (useCaseName.equalsIgnoreCase("addOptionVariableFormComponent"))
			return makeAddOptionVariableFormComponentUseCase();
		else if (useCaseName.equalsIgnoreCase("askQuestion"))
			return makeAskQuestionUseCase();
		else
			return makeHandleUnfoundUseCaseUseCase();
	}

	private UseCase makeAddUnstructuredFormComponentUseCase() {
		return new AddUnstructuredFormComponentUseCase();
	}
	
	private UseCase makeAddOptionVariableFormComponentUseCase() {
		return new AddOptionVariableFormComponentUseCase();
	}

	private UseCase makeAskQuestionUseCase() {
		return new AskQuestionUseCase();
	}

	private UseCase makeHandleUnfoundUseCaseUseCase() {
		return new HandleUnfoundUseCaseUseCase();
	}
}
