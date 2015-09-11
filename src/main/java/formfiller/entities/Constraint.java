package formfiller.entities;

import formfiller.deprecated.ConstrainableAnswer;
import formfiller.enums.ContentConstraint;

public abstract class Constraint extends ConstrainableAnswer {
	ContentConstraint name;
	ConstrainableAnswer answer;
	
	public Constraint(ContentConstraint name){
		super(ConstrainableAnswer.NONE);
		this.name = name;
		this.answer = ConstrainableAnswer.NONE;		
	}

	public void wrap(ConstrainableAnswer answer) throws IllegalArgumentException{
		if (answer == null || answer.getContent() == null)
			throw new IllegalArgumentException(
					"Constraint cannot wrap null responses or content.");
		this.answer = answer;
	}
	
	public boolean hasAnswer(){
		return !(answer.equals(ConstrainableAnswer.NONE));
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
	
	public boolean isSatisfied(){
		if (!hasAnswer()) return false;
		return isConstraintSatisfied();
	}
	
	protected abstract boolean isConstraintSatisfied();
}
