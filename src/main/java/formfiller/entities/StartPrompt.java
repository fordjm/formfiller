package formfiller.entities;

public class StartPrompt extends NoQuestion {

	public String getContent() {
		return "You have reached the start of this form.";
	}
	public String getId() {
		return "start";
	}

}
