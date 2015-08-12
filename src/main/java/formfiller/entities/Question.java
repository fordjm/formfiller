package formfiller.entities;

public class Question implements Prompt {
	String id;
	String content;
	boolean requiresResponse;

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
	
	public void setResponseRequired(boolean requiresResponse){
		this.requiresResponse = requiresResponse;
	}

	public boolean requiresAnswer() {
		return requiresResponse;
	}
}
