package formfiller.transactions;

import formfiller.entities.PromptFunctions;
import formfiller.entities.Prompt;
import formfiller.entities.ResponseConstraint;
import formfiller.persistence.PromptWidget;

public abstract class AddPrompt implements Transaction {
	String id;
	String content;

	public AddPrompt(String id, String content) {
		this.id = id;
		this.content = content;
	}

	public void execute() {
		PromptFunctions p = new Prompt(id, content);
		p.setFormat(makeFormat());
		
		PromptWidget.setPrompt(p);
	}
	
	public abstract <T> ResponseConstraint<T> makeFormat();
}
