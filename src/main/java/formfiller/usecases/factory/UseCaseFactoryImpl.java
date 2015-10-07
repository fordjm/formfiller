package formfiller.usecases.factory;

import formfiller.appBoundaries.InputBoundary;
import formfiller.usecases.addFormComponent.AddFormComponentUseCase;
import formfiller.usecases.addFormatConstraint.AddAnswerCountBoundaryUseCase;
import formfiller.usecases.addFormatConstraint.AddOptionUseCase;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.changeFormComponent.ChangeFormatUseCase;
import formfiller.usecases.changeFormComponent.ChangeIdUseCase;
import formfiller.usecases.deleteFormComponent.DeleteFormComponentUseCase;
import formfiller.usecases.handleUnfoundController.HandleUnfoundUseCaseUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {
	public InputBoundary make(String useCaseName) {
		if (useCaseName == null) useCaseName = "";

		if (useCaseName.equalsIgnoreCase("AddAnswerCountBoundary"))
			return makeAddAnswerCountBoundaryUseCase();
		else if (useCaseName.equalsIgnoreCase("AddOption"))
			return makeAddOptionUseCase();
		else if (useCaseName.equalsIgnoreCase("AddFormComponent"))
			return makeAddFormComponentUseCase();
		else if (useCaseName.equalsIgnoreCase("AskQuestion"))
			return makeAskQuestionUseCase();
		else if (useCaseName.equalsIgnoreCase("ChangeId"))
			return makeChangeIdUseCase();
		else if (useCaseName.equalsIgnoreCase("ChangeFormat"))
			return makeChangeFormatUseCase();
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
	
	private InputBoundary makeAddFormComponentUseCase() {
		return new AddFormComponentUseCase();
	}

	private InputBoundary makeAskQuestionUseCase() {
		return new AskQuestionUseCase();
	}

	private InputBoundary makeChangeIdUseCase() {
		return new ChangeIdUseCase();
	}

	private InputBoundary makeChangeFormatUseCase() {
		return new ChangeFormatUseCase();
	}

	private InputBoundary makeDeleteFormComponentUseCase() {
		return new DeleteFormComponentUseCase();
	}

	private InputBoundary makeHandleUnfoundUseCaseUseCase() {
		return new HandleUnfoundUseCaseUseCase();
	}
}
