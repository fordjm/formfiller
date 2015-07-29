package formfiller.entities;

public abstract class ResponseFormat<T> extends ConstraintDecorator<T> {
	protected AbstractResponse<T> response;
	
	public ResponseFormat(AbstractResponse<T> response){
		super(response);
		this.response = response;
	}

	public abstract boolean satisfiesConstraint();
}
