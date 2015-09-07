package formfiller.usecases.addAnswer;

import java.util.Collection;

import formfiller.entities.Answer;
import formfiller.entities.Constrainable;

public class AnswerValidator {

	public boolean isValid(Answer answer) {
		if (answer == null) return false;
		
		return hasValidFieldValues(answer) && satisfiesConstraints(answer);
	}

	private boolean hasValidFieldValues(Answer answer) {
		return answer.id >= 0 && answer.content != "";
	}

	private boolean satisfiesConstraints(Answer answer) {
		if (hasNoConstraints(answer)) return true;
		
		Constrainable constrainedValue = 
				constrainValue(answer.content, answer.constraints);
		return constrainedValue.isSatisfied();
	}

	private boolean hasNoConstraints(Answer answer) {
		return answer.constraints == null || answer.constraints.size() == 0;
	}

	private Constrainable constrainValue(Object answerContent,
			Collection<Constrainable> constraints) {
		Constrainable result = null;
		for (Constrainable constraint : constraints){
			result = constraint.constrain(answerContent);
		}
		return result;
	}
}
