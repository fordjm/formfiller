package formfiller.transactions;

import formfiller.entities.Prompt;
import formfiller.entities.PromptImpl;
import formfiller.persistence.FormWidget;

public class AddPrompt implements Transaction {
	String id;
	String content;

	public AddPrompt(String id, String content) {
		this.id = id;
		this.content = content;
	}

	public void execute() {
		Prompt p = new PromptImpl(id, content);		
		FormWidget.setPrompt(p);
	}
}
