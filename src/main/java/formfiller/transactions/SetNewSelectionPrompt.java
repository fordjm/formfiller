package formfiller.transactions;

import java.util.List;

import formfiller.entities.ResponseConstraint;
import formfiller.entities.SelectionFormat;

public class SetNewSelectionPrompt extends SetNewPrompt implements Transaction {
	private List<String> selections;

	public SetNewSelectionPrompt(String id, String content, List<String> selections) {
		super(id, content);
		this.selections = selections;
	}

	@Override
	public ResponseConstraint makeFormat() {
		return new SelectionFormat(selections);
	}
}
