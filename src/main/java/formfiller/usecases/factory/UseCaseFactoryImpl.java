package formfiller.usecases.factory;

import formfiller.appBoundaries.InputBoundary;
import formfiller.usecases.addFormComponent.AddOptionVariableFormComponentUseCase;
import formfiller.usecases.addFormComponent.AddUnstructuredFormComponentUseCase;
import formfiller.usecases.addFormatConstraint.AddAnswerCountBoundaryUseCase;
import formfiller.usecases.addFormatConstraint.AddOptionUseCase;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.changeFormComponent.ChangeIdUseCase;
import formfiller.usecases.changeFormComponent.ChangeOptionVariableUseCase;
import formfiller.usecases.changeFormComponent.ChangeUnstructuredUseCase;
import formfiller.usecases.deleteFormComponent.DeleteFormComponentUseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundUseCaseUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {
	public InputBoundary make(String useCaseName) {
		if (useCaseName == null) useCaseName = "";

		if (useCaseName.equalsIgnoreCase("AddAnswerCountBoundary"))
			return makeAddAnswerCountBoundaryUseCase();
		else if (useCaseName.equalsIgnoreCase("AddOption"))
			return makeAddOptionUseCase();
		else if (useCaseName.equalsIgnoreCase("AddOptionVariableFormComponent"))
			return makeAddOptionVariableFormComponentUseCase();
		else if (useCaseName.equalsIgnoreCase("AddUnstructuredFormComponent"))
			return makeAddUnstructuredFormComponentUseCase();
		else if (useCaseName.equalsIgnoreCase("AskQuestion"))
			return makeAskQuestionUseCase();
		else if (useCaseName.equalsIgnoreCase("ChangeId"))
			return makeChangeIdUseCase();
		else if (useCaseName.equalsIgnoreCase("ChangeUnstructured"))
			return makeChangeUnstructuredUseCase();
		else if (useCaseName.equalsIgnoreCase("ChangeOptionVariable"))
			return makeChangeOptionVariableUseCase();
		else if (useCaseName.equalsIgnoreCase("DeleteFormComponent"))
			return makeDeleteFormComponentUseCase();
		else
			return makeHandleUnfoundUseCaseUseCase();
	}

	private InputBoundary makeAddAnswerCountBoundaryUseCase() {
		return new AddAnswerCountBoundaryUseCase();
	}

	private InputBoundary makeAddOptionUseCase() {
		return new AddOptionUseCase();
	}
	
	private InputBoundary makeAddOptionVariableFormComponentUseCase() {
		return new AddOptionVariableFormComponentUseCase();
	}

	private InputBoundary makeAddUnstructuredFormComponentUseCase() {
		return new AddUnstructuredFormComponentUseCase();
	}

	private InputBoundary makeAskQuestionUseCase() {
		return new AskQuestionUseCase();
	}

	private InputBoundary makeChangeIdUseCase() {
		return new ChangeIdUseCase();
	}

	private InputBoundary makeChangeOptionVariableUseCase() {
		return new ChangeOptionVariableUseCase();
	}

	private InputBoundary makeChangeUnstructuredUseCase() {
		return new ChangeUnstructuredUseCase();
	}

	private InputBoundary makeDeleteFormComponentUseCase() {
		return new DeleteFormComponentUseCase();
	}

	private InputBoundary makeHandleUnfoundUseCaseUseCase() {
		return new HandleUnfoundUseCaseUseCase();
	}
}
