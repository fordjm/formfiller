package formfiller.entities;

public class NoPrompt implements Prompt {
	
	public NoPrompt(){
	}
	public String getContent() {
		return "";
	}
	public String getId() {
		return "";
	}
	public boolean hasAnswer() {
		return false;
	}
	public boolean requiresAnswer() {
		return false;
	}
}
