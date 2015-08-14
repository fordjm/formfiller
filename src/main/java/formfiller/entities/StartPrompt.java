package formfiller.entities;

public class StartPrompt implements Prompt {

	public String getContent() {
		return "You have reached the start of this form.";
	}
	public String getId() {
		return "start";
	}
	public boolean hasAnswer() {
		return false;
	}
	public boolean requiresAnswer() {
		return false;
	}

}
