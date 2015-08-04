package formfiller.transactions;

import formfiller.persistence.FormWidget;

public class AddResponseRequired implements Transaction {
	boolean isRequired;

	public AddResponseRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public void execute() {
		FormWidget.setRequired(isRequired);
	}
}
