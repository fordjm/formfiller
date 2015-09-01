package formfiller.transactions;

import formfiller.persistence.FormWidget;

public class AddAnswerRequired implements Transaction {
	boolean isRequired;

	public AddAnswerRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public void execute() {
		FormWidget.setRequired(isRequired);
	}
}
