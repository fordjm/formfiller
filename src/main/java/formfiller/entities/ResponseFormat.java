package formfiller.entities;

public abstract class ResponseFormat<T> extends ConstraintDecorator<T> {
	protected AbstractResponse<T> response;
	
	public ResponseFormat(AbstractResponse<T> response){
		super(response);
		this.response = response;
	}
	
	public T getContent(){
		return response.getContent();
	}

	public abstract boolean satisfiesConstraint();
}
