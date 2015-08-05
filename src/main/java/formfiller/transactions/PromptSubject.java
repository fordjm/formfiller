package formfiller.transactions;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.Prompt;
import formfiller.entities.PromptImpl;
import formfiller.persistence.FormWidget;

public class PromptSubject implements Transaction {
	List<Transaction> setupTransactions = new ArrayList<Transaction>();
	/* This interactor shouldn't exist.  The use cases "nextQuestion" and "prevQuestion"
	 * should communicate with the entity gateway which sends back the prompt id, content, 
	 * and format (for selections.)  Other ContentConstraints don't matter until we try adding a response.*/
	public PromptSubject(String promptId, String promptContent, boolean required) {
		setupTransactions.add(new AddPrompt(promptId, promptContent));
		setupTransactions.add(new AddResponseRequired(required));
	}
	
	public void execute() {	
		for (Transaction t : setupTransactions)
			t.execute();
	}

	private class AddPrompt implements Transaction {
	String id;
	String content;
	
	public AddPrompt(String id, String content) {
		this.id = id;
		this.content = content;
	}
	public void execute() {
		Prompt p = new PromptImpl(id, content);
		checkTransactionIsLegal();
		FormWidget.addPrompt(p);
	}
	private void checkTransactionIsLegal() throws IllegalArgumentException {
		if (id == null || content == null)
			throw new IllegalArgumentException("Prompts cannot contain null!");
	}
}
}
