package formfiller.usecases.addAnswer;

import formfiller.entities.Answer;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.constrainable.Constraints;

public class AnswerValidator {
	private Constraints constraints;

	public AnswerValidator() {
		this.constraints = new Constraints();
	}

	public boolean accepts(Answer answer) {
		if (answer == null) return false;
		
		return answer.isValid() && 
				constraints.areSatisfiedBy(answer.getContent());
	}

	public void addConstraint(Constrainable constraint) {
		constraints.add(constraint);
	}

	public void removeConstraint(String key) {
		constraints.remove(key);
	}
}
