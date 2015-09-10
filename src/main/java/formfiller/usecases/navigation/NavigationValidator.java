package formfiller.usecases.navigation;

import formfiller.FormFillerContext;
import formfiller.entities.Answer;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.Direction;

public class NavigationValidator {
	
	public static boolean isValidDirectionalMove(Direction direction) {
		FormComponent currentComponent = getCurrentFormComponent();
		return !isInvalidMove(direction, currentComponent);
	}
	
	private static boolean isInvalidMove(Direction direction, FormComponent component){
		return isMovingForward(direction) && 
				isAnswerRequiredButAbsent(component);
	}
	
	private static boolean isMovingForward(Direction direction) {
		return direction == Direction.FORWARD;
	}
	
	private static boolean isAnswerRequiredButAbsent(FormComponent component){
		boolean result = component.requiresAnswer && 
				component.answer == Answer.NONE;
		return result;
	}
	
	private static FormComponent getCurrentFormComponent() {
		return FormFillerContext.formComponentState.getCurrent();
	}
}
