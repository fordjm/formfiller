package formfiller.entities;

public class EndPrompt extends NoQuestion {

	public String getContent() {
		return "You have reached the end of this form.";
	}
	public String getId() {
		return "end";
	}

}
