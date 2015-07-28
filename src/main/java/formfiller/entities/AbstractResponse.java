package formfiller.entities;

public abstract class AbstractResponse<T> implements Response<T> {
	int id;
	T content;

	public AbstractResponse(int id, T content) {
		this.id = id;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public T getContent() {
		return content;
	}

	public abstract boolean satisfiesConstraint();
}
