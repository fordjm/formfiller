package formfiller.entities;

public class AnswerImpl<T> extends AbstractAnswer<T> {
	public static final Answer NONE = getNoAnswer();

	private static Answer getNoAnswer() {
		return new Answer() {
			public int getId() {
				return -1;
			}
			public String getContent() {
				return "";
			}
			public boolean satisfiesConstraint() {
				return false;
			} };
	}

	public AnswerImpl(T content){
		super(0, content);
	}

	public AnswerImpl(int id, T content){
		super(id, content);
	}

	public boolean satisfiesConstraint() {
		return id >= 0 && content != null;
	}
}
