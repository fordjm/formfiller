package formfiller.deprecated;

public class RemoveAnswer implements Transaction {
	
	public void execute() {
		checkTransactionIsLegal();
		FormWidget.clearAnswer();
	}
	
	private void checkTransactionIsLegal() throws IllegalStateException{
		if (!FormWidget.hasAnswer()) 
			throw new IllegalStateException("This question has no responses!");
	}
}