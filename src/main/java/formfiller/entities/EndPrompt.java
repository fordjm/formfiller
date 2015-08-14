package formfiller.entities;

public class EndPrompt implements Prompt {

	public String getContent() {
		return "You have reached the end of this form.";
	}
	public String getId() {
		return "end";
	}
	public boolean hasAnswer() {
		return false;
	}
	public boolean requiresAnswer() {
		return false;
	}

}
