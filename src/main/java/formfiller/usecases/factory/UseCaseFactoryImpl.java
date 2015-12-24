package formfiller.usecases.factory;

import formfiller.appBoundaries.UseCase;
import formfiller.usecases.addAnswer.AddAnswerUseCase;
import formfiller.usecases.addFormComponent.AddFormComponentUseCase;
import formfiller.usecases.addFormatConstraint.AddAnswerCountBoundaryUseCase;
import formfiller.usecases.addFormatConstraint.AddOptionUseCase;
import formfiller.usecases.askQuestion.AskQuestionUseCase;
import formfiller.usecases.changeFormComponent.ChangeFormatUseCase;
import formfiller.usecases.changeFormComponent.ChangeIdUseCase;
import formfiller.usecases.deleteFormComponent.DeleteFormComponentUseCase;
import formfiller.usecases.handleUnfoundUseCase.HandleUnfoundUseCaseUseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {
	public UseCase make(String useCaseName) {
		if (useCaseName == null) useCaseName = "";

		if (useCaseName.equalsIgnoreCase("AddAnswer"))
			return makeAddAnswerUseCase();
		else if (useCaseName.equalsIgnoreCase("AddAnswerCountBoundary"))
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

	private UseCase makeAddAnswerUseCase() {
		return new AddAnswerUseCase();
	}

	private UseCase makeAddAnswerCountBoundaryUseCase() {
		return new AddAnswerCountBoundaryUseCase();
	}

	private UseCase makeAddOptionUseCase() {
		return new AddOptionUseCase();
	}
	
	private UseCase makeAddFormComponentUseCase() {
		return new AddFormComponentUseCase();
	}

	private UseCase makeAskQuestionUseCase() {
		return new AskQuestionUseCase();
	}

	private UseCase makeChangeIdUseCase() {
		return new ChangeIdUseCase();
	}

	private UseCase makeChangeFormatUseCase() {
		return new ChangeFormatUseCase();
	}

	private UseCase makeDeleteFormComponentUseCase() {
		return new DeleteFormComponentUseCase();
	}

	private UseCase makeHandleUnfoundUseCaseUseCase() {
		return new HandleUnfoundUseCaseUseCase();
	}
}
