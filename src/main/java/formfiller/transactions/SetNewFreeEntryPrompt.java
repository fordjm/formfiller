package formfiller.transactions;

import formfiller.entities.FreeEntryFormat;
import formfiller.entities.ResponseConstraint;

public class SetNewFreeEntryPrompt extends SetNewPrompt {

	public SetNewFreeEntryPrompt(String id, String content) {
		super(id, content);
	}

	public ResponseConstraint makeFormat(){
		return new FreeEntryFormat();
	}
}
