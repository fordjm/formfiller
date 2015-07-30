package formfiller.entities;

import formfiller.utilities.ConstraintName;

public abstract class ConstraintDecorator<T> implements Response<T> {
	ConstraintName name;
	Response<T> response;
	
	public ConstraintDecorator(ConstraintName name){
		this.name = name;
		this.response = (Response<T>) new NullResponse();		
	}
//####################################################################################
	public void wrap(Response<T> response) throws IllegalArgumentException{
		if (response == null || response.getContent() == null)
			throw new IllegalArgumentException("ConstraintDecorator cannot wrap null "
					+ "responses or content.");
		this.response = response;
	}
	
	public boolean hasResponse(){
		return !(response instanceof NullResponse);
	}
	
	public ConstraintName getName(){
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
