package formfiller.usecases.addAnswer;

import java.lang.reflect.Type;

import formfiller.entities.Answer;
import formfiller.entities.constrainable.AnswerType;
import formfiller.entities.constrainable.Constrainable;
import formfiller.utilities.StringUtilities;

public class AnswerValidator {
	private Constraints constraints;

	public AnswerValidator() {
		this.constraints = new Constraints();
	}

	public boolean isValid(Answer answer) {
		if (answer == null) return false;
		
		return hasValidFieldValues(answer) && 
				constraints.areSatisfiedBy(answer.content);
	}

	//	TODO:	Fix answer content condition and move to Answer object.
	private boolean hasValidFieldValues(Answer answer) {
		return !StringUtilities.isStringNullOrEmpty(answer.questionId) && 
				answer.content != "";
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
