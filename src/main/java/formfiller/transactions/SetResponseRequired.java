package formfiller.transactions;

import formfiller.persistence.FormWidget;

public class SetResponseRequired implements Transaction {
	boolean isRequired;

	public SetResponseRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public void execute() {
		FormWidget.setRequired(isRequired);
	}
}
