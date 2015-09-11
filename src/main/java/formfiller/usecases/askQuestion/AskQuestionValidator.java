package formfiller.usecases.askQuestion;

import formfiller.FormFillerContext;
import formfiller.entities.Answer;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.WhichQuestion;

public class AskQuestionValidator {
	
	public static boolean isValidQuestion(WhichQuestion direction) {
		FormComponent currentComponent = getCurrentFormComponent();
		return !isInvalidMove(direction, currentComponent);
	}
	
	private static boolean isInvalidMove(WhichQuestion direction, FormComponent component){
		return isMovingForward(direction) && 
				isAnswerRequiredButAbsent(component);
	}
	
	private static boolean isMovingForward(WhichQuestion direction) {
		return direction == WhichQuestion.NEXT;
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
