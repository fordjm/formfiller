package formfiller.usecases.factory;

import formfiller.appBoundaries.UseCase;
import formfiller.usecases.addFormComponent.AddOptionVariableFormComponentUseCase;
import formfiller.usecases.addFormComponent.AddUnstructuredFormComponentUseCase;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.changeFormComponent.ChangeIdUseCase;
import formfiller.usecases.deleteFormComponent.DeleteFormComponentUseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundUseCaseUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {
	public UseCase make(String useCaseName) {
		if (useCaseName == null) useCaseName = "";
			
		if (useCaseName.equalsIgnoreCase("AddUnstructuredFormComponent"))
			return makeAddUnstructuredFormComponentUseCase();
		else if (useCaseName.equalsIgnoreCase("AddOptionVariableFormComponent"))
			return makeAddOptionVariableFormComponentUseCase();
		else if (useCaseName.equalsIgnoreCase("AskQuestion"))
			return makeAskQuestionUseCase();
		else if (useCaseName.equalsIgnoreCase("ChangeId"))
			return makeChangeIdUseCase();
		else if (useCaseName.equalsIgnoreCase("DeleteFormComponent"))
			return makeDeleteFormComponentUseCase();
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

	private UseCase makeChangeIdUseCase() {
		return new ChangeIdUseCase();
	}

	private UseCase makeDeleteFormComponentUseCase() {
		return new DeleteFormComponentUseCase();
	}

	private UseCase makeHandleUnfoundUseCaseUseCase() {
		return new HandleUnfoundUseCaseUseCase();
	}
}
