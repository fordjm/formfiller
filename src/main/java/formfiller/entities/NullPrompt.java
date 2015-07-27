package formfiller.entities;

public class NullPrompt implements Prompt {
	
	public NullPrompt(){
	}

	public String content() {
		return "";
	}

	public String id() {
		return "";
	}
}
