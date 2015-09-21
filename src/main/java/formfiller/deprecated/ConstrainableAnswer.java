package formfiller.deprecated;

//	TODO:	Delete after converting all Constraints to Constrainable.
public class ConstrainableAnswer {
	public static final ConstrainableAnswer NONE = new ConstrainableAnswer(-1, "");
	private DeprecatedAnswerWithId answer = new DeprecatedAnswerWithId();

	public ConstrainableAnswer(Object content){
		answer.id = 0;
		answer.content = content;
	}

	public ConstrainableAnswer(int id, Object content){
		answer.id = id;
		answer.content = content;
	}
	
	public int getId() {
		return answer.id;
	}
	
	public Object getContent() {
		return answer.content;
	}

	public boolean isSatisfied() {
		return answer.id >= 0 && answer.content != null;
	}
	
	private class DeprecatedAnswerWithId {
		public int id = -1;
		public Object content = "";
	}
}
