package formfiller.transactions;

import formfiller.entities.IPrompt;
import formfiller.entities.Prompt;
import formfiller.entities.ResponseConstraint;
import formfiller.persistence.PromptWidget;

public abstract class SetNewPrompt implements Transaction {
	String id;
	String content;

	public SetNewPrompt(String id, String content) {
		this.id = id;
		this.content = content;
	}

	public void execute() {
		IPrompt p = new Prompt(id, content);
		p.setFormat(makeFormat());
		
		PromptWidget.setPrompt(p);
	}
	
	public abstract ResponseConstraint makeFormat();

}
