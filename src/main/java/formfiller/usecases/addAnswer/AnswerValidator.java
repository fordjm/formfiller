package formfiller.usecases.addAnswer;

import java.lang.reflect.Type;

import formfiller.entities.AnswerImpl;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.entities.constrainable.Constraints;

public class AnswerValidator {
	private Constraints constraints;

	public AnswerValidator() {
		this.constraints = new Constraints();
	}

	public boolean isValid(AnswerImpl answer) {
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
	
	//	TODO:	Find better test and remove this.
	public boolean requiresType(Type type){
		AnswerType typeConstraint = (AnswerType) constraints.get("AnswerType");
		return typeConstraint != null && typeConstraint.requiresType(type);
	}
}
