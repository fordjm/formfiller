package formfiller.entities;

import formfiller.enums.ContentConstraint;

public abstract class Constraint implements Answer {
	ContentConstraint name;
	Answer response;
	
	public Constraint(ContentConstraint name){
		this.name = name;
		this.response = (Answer) new NoAnswer();		
	}

	public void wrap(Answer response) throws IllegalArgumentException{
		if (response == null || response.getContent() == null)
			throw new IllegalArgumentException(
					"Constraint cannot wrap null responses or content.");
		this.response = response;
	}
	
	public boolean hasResponse(){
		return !(response instanceof NoAnswer);
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
