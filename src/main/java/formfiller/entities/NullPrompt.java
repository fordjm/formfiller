package formfiller.entities;

public class NullPrompt implements Prompt {
	
	public NullPrompt(){
	}

	public String getContent() {
		return "";
	}

	public String getId() {
		return "";
	}
}
