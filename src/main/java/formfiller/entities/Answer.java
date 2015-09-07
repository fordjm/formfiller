package formfiller.entities;

public class Answer extends AbstractAnswer {
	public static final Answer NONE = new Answer(-1, "");

	public Answer(Object content){
		super(0, content);
	}

	public Answer(int id, Object content){
		super(id, content);
	}

	public boolean satisfiesConstraint() {
		return id >= 0 && content != null;
	}
}
