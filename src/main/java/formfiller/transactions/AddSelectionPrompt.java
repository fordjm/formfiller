package formfiller.transactions;

import java.util.List;

import formfiller.entities.ResponseConstraint;
import formfiller.entities.SelectionFormat;

public class AddSelectionPrompt<T> extends AddPrompt implements Transaction {
	private List<T> selections;

	public AddSelectionPrompt(String id, String content, List<T> selections) {
		super(id, content);
		this.selections = selections;
	}

	@Override
	public ResponseConstraint<T> makeFormat() {
		return new SelectionFormat<T>(selections);
	}
}
