package formfiller.entities;

public class ConstrainableAnswer implements Constrainable {
	public static final ConstrainableAnswer NONE = new ConstrainableAnswer(-1, "");
	private Answer answer = new Answer();

	public ConstrainableAnswer(Answer answer){ 
		this.answer = answer;
	}

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

	public Constrainable constrain(Object value) {
		return null;
	}
}
