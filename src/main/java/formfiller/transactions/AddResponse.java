package formfiller.transactions;

import formfiller.persistence.PromptWidget;

public class AddResponse implements Transaction {
	String content;

	public AddResponse(String content) {
		this.content = content;
	}

	public void execute() {
		PromptWidget.setResponse(content);
	}
}
