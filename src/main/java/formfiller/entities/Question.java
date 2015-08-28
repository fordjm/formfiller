package formfiller.entities;

public class Question implements Prompt {
	String id;
	String content;
	boolean requiresAnswer;
	public static final NoQuestion START = getStartPrompt();
	public static final NoQuestion END = getEndPrompt();

	private static NoQuestion getStartPrompt() {
		return new NoQuestion(){
			public String getContent() {
				return "You have reached the start of this form.";
			}
			public String getId() {
				return "start";
			}
		};
	}
	private static NoQuestion getEndPrompt() {
		return new NoQuestion(){
			public String getContent() {
				return "You have reached the end of this form.";
			}
			public String getId() {
				return "end";
			}
		};
	}

	public Question(String id, String content) {
		this.id = id;
		this.content = content;
	}	
	
	public String getContent(){
		return content;
	}
	public String getId() {
		return id;
	}
	public boolean hasAnswer() {
		return false;
	}	
	public void setResponseRequired(boolean requiresAnswer){
		this.requiresAnswer = requiresAnswer;
	}
	public boolean requiresAnswer() {
		return requiresAnswer;
	}
}
