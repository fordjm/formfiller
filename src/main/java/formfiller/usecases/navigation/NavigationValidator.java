package formfiller.usecases.navigation;

import formfiller.ApplicationContext;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.enums.Direction;

public class NavigationValidator {
	
	public static boolean isValidMove(Direction direction) {
		Prompt currentQuestion = getCurrentQuestion();		
		return !isInvalidMove(direction, currentQuestion);
	}
	
	private static boolean isInvalidMove(Direction direction, Prompt currentQuestion){
		return isMovingForward(direction) && 
				isAnswerRequiredButAbsent(currentQuestion);
	}
	
	private static boolean isMovingForward(Direction direction) {
		return direction == Direction.FORWARD;
	}
	
	private static boolean isAnswerRequiredButAbsent(Prompt currentQuestion){
		boolean result = currentQuestion.requiresAnswer() && 
				!currentQuestion.hasAnswer();
		return result;
	}
	
	private static Prompt getCurrentQuestion() {
		FormComponent component = getCurrentFormComponent();
		return component.question;
	}
	
	private static FormComponent getCurrentFormComponent() {
		return ApplicationContext.formComponentState.getCurrent();
	}
}
