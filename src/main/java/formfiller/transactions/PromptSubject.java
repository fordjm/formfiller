package formfiller.transactions;

import java.util.ArrayList;
import java.util.List;

import formfiller.entities.Prompt;
import formfiller.entities.PromptImpl;
import formfiller.persistence.FormWidget;

public class PromptSubject implements Transaction {
	List<Transaction> setupTransactions = new ArrayList<Transaction>();

	// Ugly comment:  This interactor should probably pull things up from the Entity Gateway.
	public PromptSubject(String promptId, String promptContent, boolean required) {
		setupTransactions.add(new AddPrompt(promptId, promptContent));
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
		FormWidget.addPrompt(p);
	}
}
}
