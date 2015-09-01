package formfiller.entities;

public class NoQuestion implements Prompt {
	
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
