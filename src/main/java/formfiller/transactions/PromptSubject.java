package formfiller.transactions;

import java.util.ArrayList;
import java.util.List;

public class PromptSubject implements Transaction {
	String promptId;
	String promptContent;

	// Ugly comment:  This interactor should probably pull things up from the Entity Gateway.
	public PromptSubject(String promptId, String promptContent) {
		this.promptId = promptId;
		this.promptContent = promptContent;
	}

	public void execute() {
		List<Transaction> setupTransactions = new ArrayList<Transaction>();
		setupTransactions.add(new AddPrompt(promptId, promptContent));
		
		for (Transaction t : setupTransactions)
			t.execute();
	}

}
