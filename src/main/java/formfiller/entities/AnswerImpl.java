package formfiller.entities;

public class AnswerImpl extends AbstractAnswer {
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

	public AnswerImpl(Object content){
		super(0, content);
	}

	public AnswerImpl(int id, Object content){
		super(id, content);
	}

	public boolean satisfiesConstraint() {
		return id >= 0 && content != null;
	}
}
