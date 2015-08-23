package formfiller.entities;

import formfiller.enums.ContentConstraint;

public abstract class Constraint implements Answer {
	ContentConstraint name;
	Answer answer;
	
	public Constraint(ContentConstraint name){
		this.name = name;
		this.answer = AnswerImpl.NONE;		
	}

	public void wrap(Answer response) throws IllegalArgumentException{
		if (response == null || response.getContent() == null)
			throw new IllegalArgumentException(
					"Constraint cannot wrap null responses or content.");
		this.answer = response;
	}
	
	public boolean hasResponse(){
		return !(answer.equals(AnswerImpl.NONE));
	}
	
	public ContentConstraint getName(){
		return name;
	}

	public int getId() {
		return answer.getId();
	}

	public <T> T getContent() {
		return answer.getContent();
	}
	
	public boolean satisfiesConstraint(){
		if (!hasResponse()) return false;
		return isConstraintSatisfied();
	}
	
	protected abstract boolean isConstraintSatisfied();
}
