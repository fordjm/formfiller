package formfiller.gateways;

import formfiller.FormFillerContext;
import formfiller.enums.WhichQuestion;

public class InMemoryTransporter implements Transporter {
	
	public void moveToElement(WhichQuestion direction){
		if (!moveChangesPosition(direction)) 
			return;
		
		getCurrentState().update(direction);
	}
	
	private static FormComponentState getCurrentState(){
		return FormFillerContext.formComponentState;
	}

	private static boolean moveChangesPosition(WhichQuestion direction) {
		if (direction == WhichQuestion.CURRENT) 
			return false;
		else if (direction == WhichQuestion.PREVIOUS && 
				getCurrentState().isAtStart())
			return false;
		else if (direction == WhichQuestion.NEXT && 
				getCurrentState().isAtEnd())
			return false;
		else
			return true;
	}
}
