package formfiller.entities;

public abstract class ResponseFormat<T> implements ResponseConstraint<T> {

	public abstract boolean satisfiesConstraint(T response);
}
