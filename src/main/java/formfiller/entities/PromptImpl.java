package formfiller.entities;

public class PromptImpl implements Prompt {
	String id;
	String content;

	public PromptImpl(String id, String content) {
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
