package formfiller.entities;

public class Question implements Prompt {
	String id;
	String content;
	boolean requiresAnswer;
	public static final Prompt START = new StartPrompt();
	public static final Prompt END = new EndPrompt();

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
