package formfiller.usecases.addAnswer;

import java.util.ArrayList;
import java.util.Collection;

import formfiller.entities.Answer;
import formfiller.entities.constrainable.Constrainable;

public class AnswerValidator {
	Collection<Constrainable> constraints;

	public AnswerValidator() {
		this.constraints = new ArrayList<Constrainable>();
	}

	public boolean isValid(Answer answer) {
		if (answer == null) return false;
		
		return hasValidFieldValues(answer) && satisfiesConstraints(answer.content);
	}

	private boolean hasValidFieldValues(Answer answer) {
		return answer.id >= 0 && answer.content != "";
	}

	private boolean satisfiesConstraints(Object object) {
		if (hasNoConstraints()) return true;
		
		for (Constrainable constraint : constraints){
			if (!constraint.isSatisfiedBy(object))
				return false;
		}
		return true;
	}

	private boolean hasNoConstraints() {
		return constraints.size() == 0;
	}

	public void addConstraint(Constrainable constraint) {
		constraints.add(constraint);
	}
}
