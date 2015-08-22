package formfiller.entities;

public class AnswerImpl<T> extends AbstractAnswer<T> {
	public static final Answer NONE = new NoAnswer();
	
	public AnswerImpl(int id, T content){
		super(id, content);
	}

	@Override
	public boolean satisfiesConstraint() {
		return id >= 0 && content != null;
	}
}
