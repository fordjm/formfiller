package formfiller.entities;

import formfiller.enums.ContentConstraint;

public abstract class Constraint extends Answer {
	ContentConstraint name;
	Answer answer;
	
	public Constraint(ContentConstraint name){
		super(Answer.NONE);
		this.name = name;
		this.answer = Answer.NONE;		
	}

	public void wrap(Answer answer) throws IllegalArgumentException{
		if (answer == null || answer.getContent() == null)
			throw new IllegalArgumentException(
					"Constraint cannot wrap null responses or content.");
		this.answer = answer;
	}
	
	public boolean hasAnswer(){
		return !(answer.equals(Answer.NONE));
	}
	
	public ContentConstraint getName(){
		return name;
	}

	public int getId() {
		return answer.getId();
	}

	public Object getContent() {
		return answer.getContent();
	}
	
	public boolean satisfiesConstraint(){
		if (!hasAnswer()) return false;
		return isConstraintSatisfied();
	}
	
	protected abstract boolean isConstraintSatisfied();
}
