package formfiller.entities;

public abstract class AbstractAnswer<T> implements Answer {
	int id;
	T content;

	public AbstractAnswer(int id, T content) {
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
