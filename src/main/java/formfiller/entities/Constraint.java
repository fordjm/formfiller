package formfiller.entities;

import formfiller.enums.ContentConstraint;

public abstract class Constraint<T> implements Response<T> {
	ContentConstraint name;
	Response<T> response;
	
	public Constraint(ContentConstraint name){
		this.name = name;
		this.response = (Response<T>) new NullResponse();		
	}

	public Constraint<T> wrap(Response<T> response) throws IllegalArgumentException{
		if (response == null || response.getContent() == null)
			throw new IllegalArgumentException(
					"ConstraintDecorator cannot wrap null responses or content.");
		this.response = response;
		return this;
	}
	
	public boolean hasResponse(){
		return !(response instanceof NullResponse);
	}
	
	public ContentConstraint getName(){
		return name;
	}

	public int getId() {
		return response.getId();
	}

	public T getContent() {
		return response.getContent();
	}
	
	public boolean satisfiesConstraint(){
		if (!hasResponse()) return false;
		return isConstraintSatisfied();
	}
	
	protected abstract boolean isConstraintSatisfied();
}
