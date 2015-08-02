package formfiller.entities;

import java.lang.reflect.Type;

import formfiller.enums.ContentConstraint;

public class ResponseType<T> extends Constraint<T> {
	Type type;

	public ResponseType(Type type) {
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
	
	private boolean responseTypeMatchesGivenType(
			T content, Type type){
		Class<? extends Object> responseClass = content.getClass();		
		return responseClass.equals(type);
	}
}
