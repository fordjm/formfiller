package formfiller.entities;

public abstract class AbstractAnswer {
	int id;
	Object content;

	public AbstractAnswer(int id, Object content) {
		this.id = id;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	
	public Object getContent() {
		return content;
	}

	public abstract boolean satisfiesConstraint();
}
