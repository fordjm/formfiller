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

	@Override
	protected boolean isConstraintSatisfied() {
		return responseTypeMatchesGivenType(
				response.getContent(), type) && 
				response.satisfiesConstraint();
	}
	
	private <T> boolean responseTypeMatchesGivenType(
			T content, Type type){
		Class<? extends Object> responseClass = content.getClass();		
		return responseClass.equals(type);
	}
}
