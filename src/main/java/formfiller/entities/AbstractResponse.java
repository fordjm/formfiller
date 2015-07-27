package formfiller.entities;

public abstract class AbstractResponse<T> implements Constraint<T> {
	T content;

	public AbstractResponse(T content) {
		this.content = content;
	}

	public T content() {
		return content;
	}

	public abstract boolean satisfiesConstraint();
}
