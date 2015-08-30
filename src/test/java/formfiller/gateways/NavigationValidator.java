package formfiller.gateways;

import formfiller.ApplicationContext;
import formfiller.entities.FormComponent;
import formfiller.entities.Prompt;
import formfiller.gateways.InMemoryTransporter.Direction;

public class NavigationValidator {
	
	public boolean isMoveLegal(Direction direction) {
		Prompt currentQuestion = getCurrentQuestion();
		return !isMovingForward(direction) || 
				!isAnswerRequiredButAbsent(currentQuestion);
	}
	
	private boolean isMovingForward(Direction direction) {
		return direction == Direction.FORWARD;
	}
	
	private boolean isAnswerRequiredButAbsent(Prompt currentQuestion){
		boolean result = currentQuestion.requiresAnswer() && 
				!currentQuestion.hasAnswer();
		return result;
	}
	
	private Prompt getCurrentQuestion() {
		return getCurrentFormComponent().question;
	}
	
	private FormComponent getCurrentFormComponent() {
		return getTransporter().getCurrent();
	}

	private Transporter getTransporter(){
		return ApplicationContext.formComponentGateway.getTransporter();
	}
}
