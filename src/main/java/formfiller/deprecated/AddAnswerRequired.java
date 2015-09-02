package formfiller.deprecated;

public class AddAnswerRequired implements Transaction {
	boolean isRequired;

	public AddAnswerRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public void execute() {
		FormWidget.setRequired(isRequired);
	}
}
