package formfiller.entities;

public class Answer {
	public static final Answer NONE = new Answer(-1, "");
	int id;
	Object content;

	public Answer(Object content){
		this.id = 0;
		this.content = content;
	}

	public Answer(int id, Object content){
		this.id = id;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	
	public Object getContent() {
		return content;
	}

	public boolean satisfiesConstraint() {
		return id >= 0 && content != null;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
