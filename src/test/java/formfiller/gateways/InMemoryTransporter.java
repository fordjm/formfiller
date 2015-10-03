package formfiller.gateways;

import formfiller.Context;
import formfiller.enums.QuestionAsked;

public class InMemoryTransporter implements Transporter {
	
	public void moveToElement(QuestionAsked direction){
		if (!moveChangesPosition(direction)) 
			return;
		
		getCurrentState().update(direction);
	}
	
	private static FormComponentState getCurrentState(){
		return Context.formComponentState;
	}

	private static boolean moveChangesPosition(QuestionAsked direction) {
		if (direction == QuestionAsked.CURRENT) 
			return false;
		else if (direction == QuestionAsked.PREVIOUS && 
				getCurrentState().isAtStart())
			return false;
		else if (direction == QuestionAsked.NEXT && 
				getCurrentState().isAtEnd())
			return false;
		else
			return true;
	}
}
