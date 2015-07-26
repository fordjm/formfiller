package formfiller.transactions;

import java.util.List;

import formfiller.entities.ResponseFormat;
import formfiller.entities.SelectionFormat;

public class AddSelectionPrompt<T> extends AddPrompt<T> implements Transaction {
	private List<T> selections;

	public AddSelectionPrompt(String id, String content, List<T> selections) {
		super(id, content);
		this.selections = selections;
	}

	@Override
	public ResponseFormat<T> makeFormat() {
		return new SelectionFormat<T>(selections);
	}
}
