package formfiller.transactions;

import formfiller.persistence.FormWidget;

public class ClearWidget implements Transaction {
	public void execute() {
		FormWidget.clear();
	}
}
