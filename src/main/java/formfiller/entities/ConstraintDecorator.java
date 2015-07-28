package formfiller.entities;

public abstract class ConstraintDecorator<T> implements Constrainable<T> {
	AbstractResponse<T> response;
	
	public ConstraintDecorator(AbstractResponse<T> response){
		this.response = response;
	}
	
	public abstract boolean satisfiesConstraint();
}
