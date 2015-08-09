package formfiller.entities;

import formfiller.enums.ContentConstraint;

public abstract class Constraint implements Response {
	ContentConstraint name;
	Response response;
	
	public Constraint(ContentConstraint name){
		this.name = name;
		this.response = (Response) new NullResponse();		
	}

	public void wrap(Response response) throws IllegalArgumentException{
		if (response == null || response.getContent() == null)
			throw new IllegalArgumentException(
					"Constraint cannot wrap null responses or content.");
		this.response = response;
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

	public <T> T getContent() {
		return response.getContent();
	}
	
	public boolean satisfiesConstraint(){
		if (!hasResponse()) return false;
		return isConstraintSatisfied();
	}
	
	protected abstract boolean isConstraintSatisfied();
}
