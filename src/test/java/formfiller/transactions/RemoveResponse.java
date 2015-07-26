package formfiller.transactions;

import formfiller.persistence.PromptWidget;

public class RemoveResponse implements Transaction {

	public void execute() {
		PromptWidget.clearResponse();
	}
}