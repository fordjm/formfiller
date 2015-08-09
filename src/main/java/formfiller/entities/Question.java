package formfiller.entities;

public class Question implements Prompt {
	String id;
	String content;

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
}
