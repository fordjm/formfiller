package formfiller.entities;

import java.lang.reflect.Type;

public class ResponseType<T> extends ConstraintDecorator<T> {
	Type type;

	public ResponseType(AbstractResponse<T> response, Type type) {
		super(response);
		this.type = type;
	}
	
	public Type getType(){
		return type;
	}

	public boolean satisfiesConstraint() {
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
