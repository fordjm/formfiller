package formfiller.transactions;

import formfiller.persistence.FormWidget;

public class RemoveResponse implements Transaction {
	public void execute() {
		checkTransactionIsLegal();
		FormWidget.clearResponse();
	}
	private void checkTransactionIsLegal() throws IllegalStateException{
		if (!FormWidget.hasResponse()) 
			throw new IllegalStateException("This question has no responses!");
	}
}