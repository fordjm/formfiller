package formfiller.transactions;

import formfiller.entities.PromptImpl;
import formfiller.entities.Prompt;
import formfiller.entities.ResponseFormat;
import formfiller.persistence.FormWidget;

public abstract class AddPrompt<T> implements Transaction {
	String id;
	String content;

	public AddPrompt(String id, String content) {
		this.id = id;
		this.content = content;
	}

	public void execute() {
		Prompt<T> p = new PromptImpl<T>(id, content);
		p.setFormat(makeFormat());
		
		FormWidget.setPrompt(p);
	}
	
	public abstract ResponseFormat<T> makeFormat();
}
