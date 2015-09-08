package formfiller.entities;

import java.lang.reflect.Type;

import formfiller.enums.ContentConstraint;

public class AnswerType extends Constraint {
	Type type;

	public AnswerType(Type type) {
		super(ContentConstraint.TYPE);
		this.type = type;
	}
	
	public Type getType(){
		return type;
	}

	protected boolean isConstraintSatisfied() {
		return responseTypeMatchesGivenType(
				answer.getContent(), type) && 
				answer.isSatisfiedBy(null);
	}
	
	private boolean responseTypeMatchesGivenType(
			Object content, Type type){
		Class<? extends Object> responseClass = content.getClass();		
		return responseClass.equals(type);
	}
}
