package formfiller.transactions;

import formfiller.persistence.FormWidget;

public class RemoveResponse implements Transaction {

	public void execute() {
		FormWidget.clearResponse();
	}
}