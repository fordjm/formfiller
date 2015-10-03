package formfiller.usecases.askQuestion;

import formfiller.Context;
import formfiller.entities.Answer;
import formfiller.entities.formComponent.FormComponent;
import formfiller.enums.QuestionAsked;

public class AskQuestionValidator {
	
	public static boolean isValidQuestion(QuestionAsked direction) {
		FormComponent currentComponent = getCurrentFormComponent();
		return !isInvalidMove(direction, currentComponent);
	}
	
	private static boolean isInvalidMove(QuestionAsked direction, FormComponent component){
		return isMovingForward(direction) && 
				isAnswerRequiredButAbsent(component);
	}
	
	private static boolean isMovingForward(QuestionAsked direction) {
		return direction == QuestionAsked.NEXT;
	}
	
	private static boolean isAnswerRequiredButAbsent(FormComponent component){
		boolean result = component.requiresAnswer && 
				component.answer == Answer.NONE;
		return result;
	}
	
	private static FormComponent getCurrentFormComponent() {
		return Context.formComponentState.getCurrent();
	}
}
